package fr.radi3nt.pathfinding.heuristic;

import fr.radi3nt.maths.components.vectors.Vector3f;

public class WeightedHeuristicDistance implements HeuristicEvaluator {

    private final ManhattanDistance manhattanDistanceEvaluator;
    private final SquaredDistance squaredDistance;
    private final float factor;

    public WeightedHeuristicDistance(ManhattanDistance manhattanDistanceEvaluator, SquaredDistance squaredDistance, float factor) {
        this.manhattanDistanceEvaluator = manhattanDistanceEvaluator;
        this.squaredDistance = squaredDistance;
        this.factor = factor;
    }

    @Override
    public float distanceBetween(Vector3f current, Vector3f end) {
        float manhattanDistance = manhattanDistanceEvaluator.distanceBetween(current, end);
        float euclideanSquared = squaredDistance.distanceBetween(current, end);
        return (factor*(manhattanDistance*manhattanDistance)+euclideanSquared)/2f;
    }

    @Override
    public float distance(float x, float y, float z) {
        float manhattanDistance = manhattanDistanceEvaluator.distance(x, y, z);
        float euclideanSquared = squaredDistance.distance(x, y, z);
        return (factor*(manhattanDistance*manhattanDistance)+euclideanSquared)/2f;
    }
}
