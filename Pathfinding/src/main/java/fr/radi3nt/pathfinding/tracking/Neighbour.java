package fr.radi3nt.pathfinding.tracking;

import fr.radi3nt.pathfinding.node.FindNode;


public class Neighbour {

    private final FindNode point;
    private final float distance;

    public Neighbour(FindNode point, float distance) {
        this.point = point;
        this.distance = distance;
    }

    public FindNode getPoint() {
        return point;
    }

    public float getDistanceFromCurrent() {
        return distance;
    }
}
