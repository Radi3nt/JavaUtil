package fr.radi3nt.maths.sat.components;

import fr.radi3nt.maths.components.Vector3D;

public class SatAxis {

    private Vector3D normal;

    public SatAxis(Vector3D normal) {
        this.normal = normal;
    }

    public void set(Vector3D normal) {
        this.normal = normal;
    }

    public double dot(Vector3D dotted) {
        return normal.dot(dotted);
    }

    @Deprecated
    public Vector3D getNormal() {
        return normal;
    }

    public SatAxis useNewNormalVector(Vector3D newNormal) {
        newNormal.setX(this.normal.getX());
        newNormal.setY(this.normal.getY());
        newNormal.setZ(this.normal.getZ());
        this.normal = newNormal;
        return this;
    }
}
