package fr.radi3nt.pathfinding.path;

import fr.radi3nt.pathfinding.node.PathNode;

public interface Path {

    PathNode getCurrentTargetPoint();
    void incrementPathPointIndex();
    int getCurrentPathPointAmount();

    boolean isFinished();

    PathNode getGoal();

    boolean isEmpty();
}
