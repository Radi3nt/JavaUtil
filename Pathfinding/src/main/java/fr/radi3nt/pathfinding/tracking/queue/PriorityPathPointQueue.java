package fr.radi3nt.pathfinding.tracking.queue;

import fr.radi3nt.pathfinding.node.FindNode;

import java.util.PriorityQueue;

public class PriorityPathPointQueue implements PathPointQueue {

    private final PriorityQueue<FindNode> finderPoints = new PriorityQueue<>();

    @Override
    public FindNode dequeue() {
        return finderPoints.poll();
    }

    @Override
    public void queue(FindNode finderPoint) {
        finderPoints.add(finderPoint);
    }

    @Override
    public boolean hasPoints() {
        return !finderPoints.isEmpty();
    }

    @Override
    public void clear() {
        finderPoints.clear();
    }

    @Override
    public void update(FindNode neighbour) {
        finderPoints.remove(neighbour);
        finderPoints.add(neighbour);
    }
}
