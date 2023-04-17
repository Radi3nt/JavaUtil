package fr.radi3nt.maths.sat.projection;

import fr.radi3nt.maths.components.Vector3D;

public class SatProjection {

    private Vector3D maxVertex;
    private Vector3D minVertex;
    private double min;
    private double max;

    private double tEnter;
    private double tLeave;
    private Vector3D collidingVertexA;
    private Vector3D collidingVertexB;

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

    public void sweptOverlap(SatProjection p2, double speed) {
        double tEnter;
        double tLeave;

        tEnter = (p2.min - this.max) / speed;
        tLeave = (p2.max - this.min) / speed;

        collidingVertexB = p2.minVertex;
        collidingVertexA = this.maxVertex;

        if (tEnter > tLeave) {
            double oldTEnter = tEnter;
            tEnter = tLeave;
            tLeave = oldTEnter;

            collidingVertexB = p2.maxVertex;
            collidingVertexA = this.minVertex;
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

    public void setMin(Vector3D minVertex, double min) {
        this.minVertex = minVertex;
        this.min = min;
    }

    public void setMax(Vector3D maxVertex, double max) {
        this.maxVertex = maxVertex;
        this.max = max;
    }

    public double getTEnter() {
        return tEnter;
    }

    public double getTLeave() {
        return tLeave;
    }

    public Vector3D getCollidingVertexA() {
        return collidingVertexA;
    }

    public Vector3D getCollidingVertexB() {
        return collidingVertexB;
    }
}
