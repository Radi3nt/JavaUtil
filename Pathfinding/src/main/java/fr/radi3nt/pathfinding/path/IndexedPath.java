package fr.radi3nt.pathfinding.path;

import fr.radi3nt.pathfinding.node.PathNode;

public class IndexedPath implements Path {

    private final PathNode[] positions;
    private int index = 0;

    public IndexedPath(PathNode[] positions) {
        this.positions = positions;
    }

    @Override
    public PathNode getCurrentTargetPoint() {
        return positions[index];
    }

    @Override
    public void incrementPathPointIndex() {
        index++;
    }

    @Override
    public int getCurrentPathPointAmount() {
        return index;
    }

    @Override
    public boolean isFinished() {
        return positions.length<=index;
    }

    @Override
    public PathNode getGoal() {
        return positions[positions.length-1];
    }

    @Override
    public boolean isEmpty() {
        return positions.length==0;
    }

    public PathNode[] getPositions() {
        return positions;
    }
}
