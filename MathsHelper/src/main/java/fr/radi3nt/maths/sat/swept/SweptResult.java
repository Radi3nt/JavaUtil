package fr.radi3nt.maths.sat.swept;

import fr.radi3nt.maths.sat.components.SatAxis;

public class SweptResult {

    private double tEnter;
    private double tLeave;
    private SatAxis enterAxis;

    public SweptResult() {
    }

    public SweptResult(double tEnter, double tLeave, SatAxis enterAxis) {
        this.tEnter = tEnter;
        this.tLeave = tLeave;
        this.enterAxis = enterAxis;
    }

    public void set(double tEnter, double tLeave, SatAxis enterAxis) {
        this.tEnter = tEnter;
        this.tLeave = tLeave;
        this.enterAxis = enterAxis;
    }

    public double getTEnter() {
        return tEnter;
    }

    public double getTLeave() {
        return tLeave;
    }

    public SatAxis getEnterAxis() {
        return enterAxis;
    }

    @Override
    public String toString() {
        return "SweptResult{" +
                "tEnter=" + tEnter +
                ", tLeave=" + tLeave +
                '}';
    }
}
