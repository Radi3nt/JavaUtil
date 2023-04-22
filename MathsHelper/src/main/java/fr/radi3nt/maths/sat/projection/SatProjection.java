package fr.radi3nt.maths.sat.projection;

public class SatProjection {

    private double min;
    private double max;

    private double tEnter;
    private double tLeave;

    public SatProjection(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public boolean noOverlap(SatProjection p2) {
        return !overlap(p2);
    }

    private boolean overlap(SatProjection p2) {
        return this.min <= p2.max && this.max >= p2.min || !(this.min <= p2.max) && this.min <= p2.min;
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
                return p2.max - this.min;
        }
        return -1d;
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
