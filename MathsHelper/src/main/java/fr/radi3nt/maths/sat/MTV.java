package fr.radi3nt.maths.sat;

import fr.radi3nt.maths.sat.components.SatAxis;

public class MTV {

    private final SatAxis smallest;
    private final double overlap;

    public MTV(SatAxis smallest, double overlap) {
        this.smallest = smallest;
        this.overlap = overlap;
    }

    public SatAxis getSmallest() {
        return smallest;
    }

    public double getOverlap() {
        return overlap;
    }
}
