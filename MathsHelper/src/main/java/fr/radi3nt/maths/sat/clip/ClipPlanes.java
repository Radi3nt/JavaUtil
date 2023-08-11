package fr.radi3nt.maths.sat.clip;

import fr.radi3nt.maths.components.Vector3D;

import java.util.ArrayList;
import java.util.Collection;

public class ClipPlanes {

    private final ClipPlane[] clipPlane;

    public ClipPlanes(ClipPlane... clipPlane) {
        this.clipPlane = clipPlane;
    }

    public Collection<Edge> clipEdges(Collection<Edge> edges) {
        Collection<Edge> resultList = new ArrayList<>(edges);

        for (ClipPlane plane : clipPlane) {
            resultList = plane.clip(resultList);
        }
        return resultList;
    }

    public Collection<Vector3D> clip(Collection<Edge> edges) {
        Collection<Edge> edgeList = clipEdges(edges);
        Collection<Vector3D> result = new ArrayList<>();
        for (Edge edge : edgeList) {
            result.add(edge.getVertex1());
            if (!edge.getVertex1().equals(edge.getVertex2()))
                result.add(edge.getVertex2());
        }
        return result;
    }

}
