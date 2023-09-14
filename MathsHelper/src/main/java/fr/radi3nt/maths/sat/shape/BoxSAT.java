package fr.radi3nt.maths.sat.shape;

import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.sat.clip.AxisAndVertexIndex;
import fr.radi3nt.maths.sat.clip.ClipPlane;
import fr.radi3nt.maths.sat.clip.ClipPlanes;
import fr.radi3nt.maths.sat.clip.Edge;
import fr.radi3nt.maths.sat.components.SatAxis;
import fr.radi3nt.maths.sat.components.SatEdge;

public class BoxSAT implements VerticesSATShape {

    private static final AxisAndVertexIndex[] SAT_CLIP_PLANES = new AxisAndVertexIndex[]{
            new AxisAndVertexIndex(new Vector3D(1, 0, 0), 1),
            new AxisAndVertexIndex(new Vector3D(0, 1, 0), 1),
            new AxisAndVertexIndex(new Vector3D(0, 0, 1), 1),
            new AxisAndVertexIndex(new Vector3D(-1, 0, 0), 0),
            new AxisAndVertexIndex(new Vector3D(0, -1, 0), 0),
            new AxisAndVertexIndex(new Vector3D(0, 0, -1), 0),
    };

    private static final SatAxis[] SAT_AXES = new SatAxis[]{
            new SatAxis(new Vector3D(1, 0, 0)),
            new SatAxis(new Vector3D(0, 1, 0)),
            new SatAxis(new Vector3D(0, 0, 1))
    };

    private static final SatEdge[] SAT_EDGES = new SatEdge[]{
            new SatEdge(new Vector3D(1, 0, 0)),
            new SatEdge(new Vector3D(0, 1, 0)),
            new SatEdge(new Vector3D(0, 0, 1))
    };

    private final Vector3D[] vertices;

    public BoxSAT(Vector3D min, Vector3D max) {
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
    }

    @Override
    public SatAxis[] getAxes() {
        return SAT_AXES;
    }

    @Override
    public SatEdge[] getEdges() {
        return SAT_EDGES;
    }

    @Override
    public ClipPlanes getClipPlanes() {
        ClipPlane[] clipPlaneArray = new ClipPlane[6];
        for (int i = 0; i < SAT_CLIP_PLANES.length; i++) {
            AxisAndVertexIndex satClipPlane = SAT_CLIP_PLANES[i];
            clipPlaneArray[i] = new ClipPlane(satClipPlane.getAxis(), vertices[satClipPlane.getIndex()]);
        }
        return new ClipPlanes(clipPlaneArray);
    }

    @Override
    public Vector3D[] getVertices() {
        return vertices;
    }

    @Override
    public Edge[] getClipEdges() {
        return new Edge[] {
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
        };
    }
}
