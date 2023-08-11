package fr.radi3nt.maths.sat.clip;

import fr.radi3nt.maths.components.Vector3D;

public class Edge {

    private final Vector3D vertex1;
    private final Vector3D vertex2;

    public Edge(Vector3D vertex1, Vector3D vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Vector3D getVertex1() {
        return vertex1;
    }

    public Vector3D getVertex2() {
        return vertex2;
    }
}
