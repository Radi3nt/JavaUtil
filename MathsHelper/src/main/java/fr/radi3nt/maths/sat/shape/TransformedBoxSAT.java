package fr.radi3nt.maths.sat.shape;

import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;
import fr.radi3nt.maths.components.vectors.Vector4f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector4f;
import fr.radi3nt.maths.sat.clip.AxisAndVertexIndex;
import fr.radi3nt.maths.sat.clip.ClipPlane;
import fr.radi3nt.maths.sat.clip.ClipPlanes;
import fr.radi3nt.maths.sat.clip.Edge;
import fr.radi3nt.maths.sat.components.SatAxis;
import fr.radi3nt.maths.sat.components.SatEdge;

import java.util.Arrays;
import java.util.Collection;

public class TransformedBoxSAT implements VerticesSATShape {

    private static final AxisAndVertexIndex[] SAT_CLIP_PLANES = new AxisAndVertexIndex[]{
            new AxisAndVertexIndex(new Vector3D(1, 0, 0), 0),
            new AxisAndVertexIndex(new Vector3D(0, 1, 0), 0),
            new AxisAndVertexIndex(new Vector3D(0, 0, 1), 0),
            new AxisAndVertexIndex(new Vector3D(-1, 0, 0), 1),
            new AxisAndVertexIndex(new Vector3D(0, -1, 0), 1),
            new AxisAndVertexIndex(new Vector3D(0, 0, -1), 1),
    };

    private final Vector3D[] vertices;

    public TransformedBoxSAT(Vector3D min, Vector3D max, Matrix4x4 transform) {
        vertices = new Vector3D[]{
                min.clone(),
                max.clone(),
                new Vector3D(max.getX(), min.getY(), min.getZ()),
                new Vector3D(max.getX(), max.getY(), min.getZ()),
                new Vector3D(min.getX(), max.getY(), min.getZ()),
                new Vector3D(min.getX(), max.getY(), max.getZ()),
                new Vector3D(min.getX(), min.getY(), max.getZ()),
                new Vector3D(max.getX(), min.getY(), max.getZ())
        };
        for (Vector3D vertex : vertices) {
            Vector4f transformed = new SimpleVector4f((float) vertex.getX(), (float) vertex.getY(), (float) vertex.getZ(), 1);
            transform.transform(transformed);

            vertex.set(transformed.getX(), transformed.getY(), transformed.getZ());
        }
    }

    @Override
    public SatAxis[] getAxes() {
        return computeAxis();
    }

    private SatAxis[] computeAxis() {
        SatAxis[] axes = new SatAxis[3];
        Vector3D[] axis = computeBoxAxes();
        for (int i = 0; i < axes.length; i++) {
            axes[i] = new SatAxis(axis[i]);
        }
        return axes;
    }

    @Override
    public SatEdge[] getEdges() {
        return computeEdges();
    }

    @Override
    public ClipPlanes getClipPlanes() {
        ClipPlane[] clipPlaneArray = new ClipPlane[SAT_CLIP_PLANES.length];
        for (int i = 0; i < SAT_CLIP_PLANES.length; i++) {
            AxisAndVertexIndex satClipPlane = SAT_CLIP_PLANES[i];
            clipPlaneArray[i] = new ClipPlane(satClipPlane.getAxis(), vertices[satClipPlane.getIndex()]);
        }
        return new ClipPlanes(clipPlaneArray);
    }

    private SatEdge[] computeEdges() {
        SatEdge[] edges = new SatEdge[3];
        Vector3D[] axis = computeBoxAxes();
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new SatEdge(axis[i]);
        }
        return edges;
    }

    private Vector3D[] computeBoxAxes() {
        Vector3D[] axis = new Vector3D[3];
        axis[0] = vertices[6].clone().subtract(vertices[0]).normalize();
        axis[1] = vertices[4].clone().subtract(vertices[0]).normalize();
        axis[2] = vertices[2].clone().subtract(vertices[0]).normalize();
        return axis;
    }

    @Override
    public Vector3D[] getVertices() {
        return vertices;
    }

    @Override
    public Collection<Edge> getClipEdges() {
        return Arrays.asList(
                new Edge(vertices[0], vertices[6]),
                new Edge(vertices[0], vertices[4]),
                new Edge(vertices[0], vertices[2]),

                new Edge(vertices[1], vertices[7]),
                new Edge(vertices[1], vertices[5]),
                new Edge(vertices[1], vertices[3]),

                new Edge(vertices[6], vertices[7]),
                new Edge(vertices[7], vertices[2]),

                new Edge(vertices[5], vertices[4]),
                new Edge(vertices[4], vertices[3]),

                new Edge(vertices[5], vertices[6]),
                new Edge(vertices[3], vertices[2])
        );
    }
}
