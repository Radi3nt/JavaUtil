package fr.radi3nt.maths.sat.clip;

import fr.radi3nt.maths.components.Vector3D;

import java.util.ArrayList;
import java.util.Collection;

public class ClipPlane {

    private final Vector3D normal;
    private final Vector3D vertexOnPlane;

    public ClipPlane(Vector3D normal, Vector3D vertexOnPlane) {
        this.normal = normal;
        this.vertexOnPlane = vertexOnPlane;
    }

    public Collection<Edge> clip(Iterable<Edge> otherEdges) {
        Collection<Edge> clippedEdges = new ArrayList<>();
        double projectedClip = normal.dot(vertexOnPlane);

        for (Edge edge : otherEdges) {
            double projectedVertex1 = normal.dot(edge.getVertex1());
            double projectedVertex2 = normal.dot(edge.getVertex2());

            if (projectedVertex1>=projectedClip && projectedVertex2>=projectedClip)
                clippedEdges.add(edge);
            else {
                if (projectedVertex1>=projectedClip) {
                    double on = (projectedClip-projectedVertex1)/(projectedVertex2-projectedVertex1);
                    Vector3D computedNewVertex = computeInterpolation(edge.getVertex1(), edge.getVertex2(), on);
                    clippedEdges.add(new Edge(edge.getVertex1(), computedNewVertex));

                } else if (projectedVertex2>=projectedClip) {
                    double on = (projectedClip-projectedVertex2)/(projectedVertex1-projectedVertex2);
                    Vector3D computedNewVertex = computeInterpolation(edge.getVertex2(), edge.getVertex1(), on);
                    clippedEdges.add(new Edge(computedNewVertex, edge.getVertex2()));

                }
            }
        }
        return clippedEdges;
    }

    private Vector3D computeInterpolation(Vector3D vertex1, Vector3D vertex2, double on) {
        Vector3D edgeLocal = vertex2.clone().subtract(vertex1);
        return vertex1.clone().add(edgeLocal.multiply(on));
    }
}
