package fr.radi3nt.maths.sat.swept;

import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.sat.components.SatAxis;

public class SweptResult {

    public static final SweptResult NO_CONTACT = new SweptResult(false, Double.MAX_VALUE, Double.MIN_VALUE, null);

    private boolean contact;
    private double tEnter;
    private double tLeave;
    private SatAxis enterAxis;
    private Vector3D contactPoint;

    public SweptResult() {
    }


    public SweptResult(double tEnter, double tLeave, SatAxis enterAxis) {
        this.contact = true;
        this.tEnter = tEnter;
        this.tLeave = tLeave;
        this.enterAxis = enterAxis;
    }

    public SweptResult(boolean contact, double tEnter, double tLeave, SatAxis enterAxis) {
        this.contact = contact;
        this.tEnter = tEnter;
        this.tLeave = tLeave;
        this.enterAxis = enterAxis;
    }

    public SweptResult(boolean contact, double tEnter, double tLeave, SatAxis enterAxis, Vector3D contactPoint) {
        this.contact = contact;
        this.tEnter = tEnter;
        this.tLeave = tLeave;
        this.enterAxis = enterAxis;
        this.contactPoint = contactPoint;
    }

    public void set(Vector3D vertex, boolean contact, double tEnter, double tLeave, SatAxis enterAxis) {
        this.contact = contact;
        this.tEnter = tEnter;
        this.tLeave = tLeave;
        this.enterAxis = enterAxis;
        this.contactPoint = vertex;
    }

    public Vector3D getContactPoint() {
        return contactPoint;
    }

    public boolean isContact() {
        return contact;
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
                "contact=" + contact +
                ", tEnter=" + tEnter +
                ", tLeave=" + tLeave +
                ", enterAxis=" + enterAxis +
                '}';
    }
}
