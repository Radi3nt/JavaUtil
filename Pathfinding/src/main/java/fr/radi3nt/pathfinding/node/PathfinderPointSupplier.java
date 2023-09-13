package fr.radi3nt.pathfinding.node;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.pathfinding.tracking.Neighbour;

public interface PathfinderPointSupplier {
    Neighbour[] getNeighbours(FindNode current);
    FindNode pointFromPosition(Vector3f start);
    PathNode nodeForPoint(FindNode current);

    void prepare();
}
