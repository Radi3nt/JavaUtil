package fr.radi3nt.maths.sat.clip;

import fr.radi3nt.maths.components.Vector3D;

import java.util.ArrayList;
import java.util.Collection;

public class ClipPlanes {

    private static final double EPSILON = 1e-2f;
    private final ClipPlane[] clipPlane;

    public ClipPlanes(ClipPlane... clipPlane) {
        this.clipPlane = clipPlane;
    }

    public Edge[] clipEdges(Edge[] edges) {
        for (ClipPlane plane : clipPlane) {
            plane.clip(edges);
        }
        return edges;
    }

    public Collection<Vector3D> clip(Edge[] edges) {
        Edge[] edgeList = clipEdges(edges);
        Collection<Vector3D> result = new ArrayList<>(edgeList.length);
        for (Edge edge : edgeList) {
            if (edge==null)
                continue;
            result.add(edge.getVertex1());
            if (notSameVertices(edge))
                result.add(edge.getVertex2());
        }
        return result;
    }

    private static boolean notSameVertices(Edge edge) {
        return edge.getVertex1().clone().subtract(edge.getVertex2()).lengthSquared() > EPSILON;
    }

}
