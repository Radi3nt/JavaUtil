package fr.radi3nt.maths.sat.projection;

public class OverlapInfo {

    private final boolean overlap;
    private final double overlapAmount;

    public OverlapInfo(boolean overlap, double overlapAmount) {
        this.overlap = overlap;
        this.overlapAmount = overlapAmount;
    }

    public boolean isOverlap() {
        return overlap;
    }

    public double getOverlapAmount() {
        return overlapAmount;
    }
}
