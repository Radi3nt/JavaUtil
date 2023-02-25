package fr.radi3nt.maths.sat.projection;

import fr.radi3nt.maths.sat.swept.SweptResult;

public class SatProjection {

    private double min;
    private double max;

    public SatProjection(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public boolean noOverlap(SatProjection p2) {
        return !overlap(p2);
    }

    private boolean overlap(SatProjection p2) {
        if (this.min <= p2.min) {
            return this.max >= p2.min;
        } else {
            return p2.max >= this.min;
        }
    }

    public void sweptOverlap(SatProjection p2, double speed, SweptResult resultPassing) {
        double tEnter;
        double tLeave;


        tEnter = (p2.min - this.max) / speed;
        tLeave = (p2.max - this.min) / speed;

        if (tEnter > tLeave) {
            double oldTEnter = tEnter;
            tEnter = tLeave;
            tLeave = oldTEnter;
        }

        resultPassing.set(tEnter, tLeave, null);
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
}
