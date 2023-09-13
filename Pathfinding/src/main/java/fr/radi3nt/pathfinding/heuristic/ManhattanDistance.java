package fr.radi3nt.pathfinding.heuristic;

import fr.radi3nt.maths.components.vectors.Vector3f;


public class ManhattanDistance implements HeuristicEvaluator {
    @Override
    public float distanceBetween(Vector3f current, Vector3f end) {
        float xDistance = Math.abs(current.getX() - end.getX());
        float yDistance = Math.abs(current.getY() - end.getY());
        float zDistance = Math.abs(current.getZ() - end.getZ());
        return xDistance + yDistance + zDistance;
    }

    @Override
    public float distance(float x, float y, float z) {
        float xDistance = Math.abs(x);
        float yDistance = Math.abs(y);
        float zDistance = Math.abs(z);
        return xDistance + yDistance + zDistance;
    }
}
