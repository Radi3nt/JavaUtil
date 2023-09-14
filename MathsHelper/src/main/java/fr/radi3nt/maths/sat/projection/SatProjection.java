package fr.radi3nt.maths.sat.projection;

import static java.lang.Math.abs;

public class SatProjection {

    private static final OverlapInfo NO_OVERLAP = new OverlapInfo(false, 0);

    public double min;
    public double max;

    private double tEnter;
    private double tLeave;

    public SatProjection(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public OverlapInfo overlapInfo(SatProjection p2) {
        if (this.min <= p2.min) {
            if (this.max >= p2.min)
                return new OverlapInfo(true, abs(p2.min - this.max));
        } else {
            if (this.min <= p2.max)
                return new OverlapInfo(true, abs(this.min - p2.max));
        }
        return NO_OVERLAP;
    }

    public boolean contains(SatProjection p2) {
        return (min <= p2.min && max >= p2.max);
    }

    public boolean noOverlap(SatProjection p2) {
        return !overlap(p2);
    }

    public boolean overlap(SatProjection p2) {
        return this.min <= p2.max ? this.max >= p2.min : this.min <= p2.min;
    }

    public boolean overlap(double p2) {
        return this.min < p2 && this.max > p2;
    }

    public void sweptOverlap(SatProjection p2, double speed) {
        double tEnter;
        double tLeave;

        tEnter = (p2.min - this.max) / speed;
        tLeave = (p2.max - this.min) / speed;

        if (tEnter > tLeave) {
            double oldTEnter = tEnter;
            tEnter = tLeave;
            tLeave = oldTEnter;
        }

        this.tEnter = tEnter;
        this.tLeave = tLeave;
    }

    public double getOverlap(SatProjection p2) {
        if (this.min <= p2.min) {
            if (this.max >= p2.min)
                return p2.min - this.max;
        } else {
            if (p2.max >= this.min)
                return this.min - p2.max;
        }
        return Double.MAX_VALUE;
    }

    public int getOverlapNormal(SatProjection p2) {
        if (this.min <= p2.min) {
            if (this.max >= p2.min)
                return 1;
        } else {
            if (p2.max >= this.min)
                return -1;
        }
        return 0;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getTEnter() {
        return tEnter;
    }

    public double getTLeave() {
        return tLeave;
    }


    @Override
    public String toString() {
        return "SatProjection{" +
                "min=" + min +
                ", max=" + max +
                ", tEnter=" + tEnter +
                ", tLeave=" + tLeave +
                '}';
    }
}
