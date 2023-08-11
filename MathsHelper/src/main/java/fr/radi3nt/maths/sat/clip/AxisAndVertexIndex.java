package fr.radi3nt.maths.sat.clip;

import fr.radi3nt.maths.components.Vector3D;

public class AxisAndVertexIndex {

    private final Vector3D axis;
    private final int index;

    public AxisAndVertexIndex(Vector3D axis, int index) {
        this.axis = axis;
        this.index = index;
    }

    public Vector3D getAxis() {
        return axis;
    }

    public int getIndex() {
        return index;
    }
}
