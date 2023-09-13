package fr.radi3nt.pathfinding.heuristic;

import fr.radi3nt.maths.components.vectors.Vector3f;


public interface HeuristicEvaluator {
    float distanceBetween(Vector3f current, Vector3f end);
    float distance(float x, float y, float z);

}
