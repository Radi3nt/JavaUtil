package fr.radi3nt.pathfinding.tracking.queue;

import fr.radi3nt.pathfinding.node.FindNode;

public interface PathPointQueue {

    FindNode dequeue();
    void queue(FindNode finderPoint);

    boolean hasPoints();
    void clear();

    void update(FindNode neighbour);
}
