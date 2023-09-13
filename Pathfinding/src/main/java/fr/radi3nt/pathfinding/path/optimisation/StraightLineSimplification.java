package fr.radi3nt.pathfinding.path.optimisation;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.pathfinding.node.PathNode;

public class StraightLineSimplification implements SimplifyPredicate {

    private final float threshold;

    public StraightLineSimplification(float threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean canSimplify(Vector3f directionToNext, Vector3f lastDirection, PathNode lastPos, PathNode currentPos, PathNode nextPos) {
        return directionToNext.duplicate().sub(lastDirection).lengthSquared()<threshold;
    }
}
