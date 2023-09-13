package fr.radi3nt.pathfinding.path.optimisation;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.pathfinding.node.PathNode;

public interface SimplifyPredicate {

    boolean canSimplify(Vector3f directionToNext, Vector3f lastDirection, PathNode lastPos, PathNode currentPos, PathNode nextPos);

}
