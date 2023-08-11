package fr.radi3nt.maths.sat.components;

import fr.radi3nt.maths.components.Vector3D;

public class SatEdge {

    private final Vector3D edge;

    public SatEdge(Vector3D edge) {
        this.edge = edge;
    }

    public SatAxis axis(SatEdge other) {
        return new SatAxis(other.edge.getCrossProduct(edge, new Vector3D()).normalize());
    }

    public SatAxis axis(SatEdge other, Vector3D result) {
        return new SatAxis(other.edge.getCrossProduct(edge, result).normalize());
    }
}
