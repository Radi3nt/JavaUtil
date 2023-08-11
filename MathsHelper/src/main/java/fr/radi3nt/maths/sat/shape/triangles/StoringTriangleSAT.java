package fr.radi3nt.maths.sat.shape.triangles;

import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.sat.clip.ClipPlane;
import fr.radi3nt.maths.sat.clip.ClipPlanes;
import fr.radi3nt.maths.sat.clip.Edge;
import fr.radi3nt.maths.sat.components.SatAxis;
import fr.radi3nt.maths.sat.components.SatEdge;

import java.util.Arrays;
import java.util.Collection;

public class StoringTriangleSAT implements TriangleSAT {

    private final Vector3D[] vertices = new Vector3D[3];
    private final SatAxis[] satAxes;
    private final SatEdge[] edges;

    public StoringTriangleSAT(Vector3f vert1, Vector3f vert2, Vector3f vert3, Vector3f triNormal) {
        for (int i = 0; i < 3; i++) {
            Vector3f position = i == 0 ? vert1 : i==1 ? vert2 : vert3;
            vertices[i] = new Vector3D(position.getX(), position.getY(), position.getZ());
        }

        Vector3D normal = new Vector3D(triNormal.getX(), triNormal.getY(), triNormal.getZ());
        SatAxis faceAxis = new SatAxis(normal);

        satAxes = new SatAxis[]{faceAxis};

        Vector3D[] vertices = getVertices();
        edges = new SatEdge[]{
                new SatEdge(vertices[0].clone().subtract(vertices[1])),
                new SatEdge(vertices[1].clone().subtract(vertices[2])),
                new SatEdge(vertices[2].clone().subtract(vertices[0])),
        };
    }

    @Override
    public Vector3D[] getVertices() {
        return vertices;
    }

    @Override
    public SatAxis[] getAxes() {
        return satAxes;
    }

    @Override
    public SatEdge[] getEdges() {
        return edges;
    }

    @Override
    public Collection<Edge> getClipEdges() {
        return Arrays.asList(
                new Edge(vertices[0], vertices[1]),
                new Edge(vertices[1], vertices[2]),
                new Edge(vertices[2], vertices[0])
        );
    }

    @Override
    public ClipPlanes getClipPlanes() {
        ClipPlane[] clipPlaneArray = new ClipPlane[] {
                new ClipPlane(getAxes()[0].getNormal(), vertices[0])
        };
        return new ClipPlanes(clipPlaneArray);
    }
}
