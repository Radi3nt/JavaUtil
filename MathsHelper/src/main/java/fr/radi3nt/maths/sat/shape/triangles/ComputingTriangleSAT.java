package fr.radi3nt.maths.sat.shape.triangles;

import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.sat.components.SatAxis;
import fr.radi3nt.maths.sat.components.SatEdge;

public class ComputingTriangleSAT implements TriangleSAT {

    private final Vector3f vert1;
    private final Vector3f vert2;
    private final Vector3f vert3;
    private final Vector3f triNormal;

    public ComputingTriangleSAT(Vector3f vert1, Vector3f vert2, Vector3f vert3, Vector3f triNormal) {
        this.vert1 = vert1;
        this.vert2 = vert2;
        this.vert3 = vert3;
        this.triNormal = triNormal;
    }

    @Override
    public Vector3D[] getVertices() {
        Vector3D[] vertices = new Vector3D[3];
        for (int i = 0; i < 3; i++) {
            Vector3f position = i == 0 ? vert1 : i==1 ? vert2 : vert3;
            vertices[i] = new Vector3D(position.getX(), position.getY(), position.getZ());
        }
        return vertices;
    }

    @Override
    public SatAxis[] getAxes() {
        Vector3D normal = new Vector3D(triNormal.getX(), triNormal.getY(), triNormal.getZ());
        SatAxis faceAxis = new SatAxis(normal);
        return new SatAxis[]{faceAxis};
    }

    @Override
    public SatEdge[] getEdges() {
        return new SatEdge[]{
                new SatEdge(sub(vert1, vert2)),
                new SatEdge(sub(vert2, vert3)),
                new SatEdge(sub(vert3, vert1)),
        };
    }

    private Vector3D sub(Vector3f vert1, Vector3f vert2) {
        return new Vector3D(vert1.getX()-vert2.getX(), vert1.getY()-vert2.getY(), vert1.getZ()-vert2.getZ());
    }
}
