package fr.radi3nt.pathfinding.node;

public class CostData {

    public final float currentWeight;
    public float distanceFromStart;
    public float accumulatedCostFromStart;
    private float evaluatedCost;

    public CostData(float currentWeight) {
        this.currentWeight = currentWeight;
    }

    public void set(float distanceFromStart, float accumulatedCostFromStart, float distanceFromEnd) {
        this.distanceFromStart = distanceFromStart;
        this.accumulatedCostFromStart = accumulatedCostFromStart;
        evaluatedCost = this.accumulatedCostFromStart+distanceFromEnd;
    }

    public float getEvaluatedCost() {
        return evaluatedCost;
    }
}
