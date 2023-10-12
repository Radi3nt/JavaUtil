package fr.radi3nt.pathfinding;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.pathfinding.heuristic.HeuristicEvaluator;
import fr.radi3nt.pathfinding.node.*;
import fr.radi3nt.pathfinding.path.IndexedPath;
import fr.radi3nt.pathfinding.path.Path;
import fr.radi3nt.pathfinding.path.optimisation.PathOptimiser;
import fr.radi3nt.pathfinding.tracking.Neighbour;
import fr.radi3nt.pathfinding.tracking.queue.PathPointQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AStarPathfinder {

    private static final int MAX_ITERATIONS = 100_000;
    private final PathPointQueue pathQueue;
    private final PathfinderPointSupplier pathfinderPointSupplier;
    private final HeuristicEvaluator heuristicEvaluator;
    private final Collection<PathOptimiser> pathOptimisers;
    private final float reachThreshold;
    private final float pathfindingLimit;

    public AStarPathfinder(PathPointQueue pathBuilder, PathfinderPointSupplier pathfinderPointSupplier, HeuristicEvaluator distanceCalculator, Collection<PathOptimiser> pathOptimisers, float reachThreshold, float pathfindingLimit) {
        this.pathQueue = pathBuilder;
        this.pathfinderPointSupplier = pathfinderPointSupplier;
        this.heuristicEvaluator = distanceCalculator;
        this.pathOptimisers = pathOptimisers;
        this.reachThreshold = reachThreshold;
        this.pathfindingLimit = pathfindingLimit;
    }

    public Path find(Vector3f start, Vector3f end) {
        pathQueue.clear();
        pathfinderPointSupplier.prepare();

        FindNode startPoint = pathfinderPointSupplier.pointFromPosition(start);
        if (isEnd(end, startPoint))
            return new IndexedPath(new PathNode[0]);

        FindNode lastPoint = startPoint;
        float lastDistanceToEnd = heuristicEvaluator.distanceBetween(startPoint.getFloatPosition(), end);
        startPoint.set(0, 0, lastDistanceToEnd);

        pathQueue.queue(startPoint);

        boolean endFound = false;

        int iterations = 0;
        while (pathQueue.hasPoints() && !endFound && iterations<MAX_ITERATIONS) {
            FindNode current = pathQueue.dequeue();
            CostData currentCostData = current.getCostData();

            current.visit();

            for (Neighbour neighbour : pathfinderPointSupplier.getNeighbours(current)) {
                if (neighbour==null)
                    continue;

                FindNode neighbourPoint = neighbour.getPoint();
                CostData neighbourCostData = neighbourPoint.getCostData();

                if (neighbourPoint.getStatus()== NodeStatus.VISITED)
                    continue;

                float neighbourDistanceFromCurrent = neighbour.getDistanceFromCurrent();
                float distanceFromStart = currentCostData.distanceFromStart + neighbourDistanceFromCurrent;
                float travelCost = neighbourDistanceFromCurrent + neighbourCostData.currentWeight;
                float newAccumulatedDistanceFromStart = currentCostData.accumulatedCostFromStart + travelCost;

                if (distanceFromStart >= pathfindingLimit)
                    continue;

                boolean opened = neighbourPoint.getStatus()== NodeStatus.OPENED;
                if (opened && neighbourCostData.accumulatedCostFromStart<newAccumulatedDistanceFromStart)
                    continue;

                float distanceToEnd = heuristicEvaluator.distanceBetween(neighbourPoint.getFloatPosition(), end);
                neighbourPoint.set(distanceFromStart, newAccumulatedDistanceFromStart, distanceToEnd, current);

                boolean isEnd = isEnd(end, neighbourPoint);
                if (distanceToEnd<lastDistanceToEnd || isEnd) {
                    lastPoint = neighbour.getPoint();
                    lastDistanceToEnd = distanceToEnd;
                }
                if (isEnd) {
                    endFound = true;
                    break;
                }

                if (opened) {
                    pathQueue.update(neighbourPoint);
                } else {
                    neighbourPoint.open();
                    pathQueue.queue(neighbourPoint);
                }
            }
            iterations++;
        }

        return generatePathFromEndNode(lastPoint);
    }

    private Path generatePathFromEndNode(FindNode lastPoint) {
        List<PathNode> pathNodes = new ArrayList<>();

        FindNode current = lastPoint;
        while (current!=null) {
            pathNodes.add(pathfinderPointSupplier.nodeForPoint(current));
            current = current.getPrevious();
        }

        Collections.reverse(pathNodes);

        for (PathOptimiser pathOptimiser : pathOptimisers) {
            pathNodes = pathOptimiser.optimise(pathNodes);
        }

        return new IndexedPath(pathNodes.toArray(new PathNode[0]));
    }

    private boolean isEnd(Vector3f end, FindNode current) {
        return current.getFloatPosition().duplicate().sub(end).lengthSquared()<reachThreshold*reachThreshold;
    }

}
