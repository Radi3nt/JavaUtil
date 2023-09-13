package fr.radi3nt.pathfinding.path.optimisation;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.pathfinding.node.PathNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SimplifyPathOptimiser implements PathOptimiser {

    private final Collection<SimplifyPredicate> simplifyPredicates;

    public SimplifyPathOptimiser(Collection<SimplifyPredicate> simplifyPredicates) {
        this.simplifyPredicates = simplifyPredicates;
    }

    public SimplifyPathOptimiser(SimplifyPredicate... simplifyPredicates) {
        this(new ArrayList<>(Arrays.asList(simplifyPredicates)));
    }

    @Override
    public List<PathNode> optimise(List<PathNode> pathNodes) {
        List<PathNode> result = new ArrayList<>();
        PathNode lastNode = pathNodes.get(0);
        Vector3f lastDirection = null;

        result.add(pathNodes.get(0));

        for (int i = 1; i < pathNodes.size()-1; i++) {
            PathNode currentNode = pathNodes.get(i);
            PathNode nextNode = pathNodes.get(i+1);
            Vector3f currentPos = currentNode.getPosition();
            Vector3f nextPos = nextNode.getPosition();

            if (lastDirection==null) {
                lastDirection = currentPos.duplicate().sub(lastNode.getPosition());
                lastDirection.normalize();
            }

            Vector3f directionToNext = nextPos.duplicate().sub(currentPos);
            directionToNext.normalize();

            if (canSimplify(directionToNext, lastDirection, lastNode, currentNode, nextNode))
                continue;

            result.add(currentNode);
            lastNode = currentNode;
            lastDirection = null;
        }

        result.add(pathNodes.get(pathNodes.size()-1));

        return result;
    }

    protected boolean canSimplify(Vector3f directionToNext, Vector3f lastDirection, PathNode lastPos, PathNode currentPos, PathNode nextPos) {
        for (SimplifyPredicate simplifyPredicate : simplifyPredicates) {
            if (simplifyPredicate.canSimplify(directionToNext, lastDirection, lastPos, currentPos, nextPos))
                return true;
        }
        return false;
    }
}
