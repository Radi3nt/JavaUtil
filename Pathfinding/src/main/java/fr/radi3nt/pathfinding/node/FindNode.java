package fr.radi3nt.pathfinding.node;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector3i;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

import java.util.Objects;

public class FindNode implements Comparable<FindNode> {

    private final Vector3i position;
    private final CostData costData;
    private FindNode previous;
    private NodeStatus nodeStatus = NodeStatus.UNDISCOVERED;

    public FindNode(Vector3i position, float weight) {
        this.position = position;
        this.costData = new CostData(weight);
    }

    public void open() {
        nodeStatus = NodeStatus.OPENED;
    }

    public void visit() {
        nodeStatus = NodeStatus.VISITED;
    }

    public NodeStatus getStatus() {
        return nodeStatus;
    }

    public void set(float distanceFromStart, float accumulatedCostFromStart, float distanceFromEnd) {
        costData.set(distanceFromStart, accumulatedCostFromStart, distanceFromEnd);
    }

    public void set(float distanceFromStart, float accumulatedCostFromStart, float distanceFromEnd, FindNode previous) {
        set(distanceFromStart, accumulatedCostFromStart, distanceFromEnd);
        this.previous = previous;
    }

    public boolean isCurrentPathFaster(float accumulatedOther) {
        return costData.accumulatedCostFromStart<accumulatedOther;
    }

    public float getEvaluatedCost() {
        return costData.getEvaluatedCost();
    }

    public FindNode getPrevious() {
        return previous;
    }

    public CostData getCostData() {
        return costData;
    }

    public Vector3i getPosition() {
        return position;
    }

    public Vector3f getFloatPosition() {
        return new SimpleVector3f(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FindNode)) return false;

        FindNode findNode = (FindNode) o;

        return Objects.equals(position, findNode.position);
    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }

    @Override
    public int compareTo(FindNode o) {
        return Float.compare(getEvaluatedCost(), o.getEvaluatedCost());
    }
}
