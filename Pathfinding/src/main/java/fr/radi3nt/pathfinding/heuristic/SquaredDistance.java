package fr.radi3nt.pathfinding.heuristic;

import fr.radi3nt.maths.components.vectors.Vector3f;


public class SquaredDistance implements HeuristicEvaluator {
    @Override
    public float distanceBetween(Vector3f current, Vector3f end) {
        float xDistance = current.getX() - end.getX();
        float yDistance = current.getY() - end.getY();
        float zDistance = current.getZ() - end.getZ();
        return xDistance*xDistance + yDistance*yDistance + zDistance*zDistance;
    }

    @Override
    public float distance(float x, float y, float z) {
        return x*x+y*y+z*z;
    }
}
