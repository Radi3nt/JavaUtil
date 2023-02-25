package fr.radi3nt.maths.sat.shape;

import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.sat.components.SatAxis;
import fr.radi3nt.maths.sat.components.SatEdge;

public class BoxSAT implements VerticesSATShape {

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
    public Vector3D[] getVertices() {
        return vertices;
    }
}
