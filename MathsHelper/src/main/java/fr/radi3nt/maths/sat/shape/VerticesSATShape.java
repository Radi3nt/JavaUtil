package fr.radi3nt.maths.sat.shape;

import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.sat.clip.ClipPlanes;
import fr.radi3nt.maths.sat.clip.Edge;
import fr.radi3nt.maths.sat.components.SatAxis;
import fr.radi3nt.maths.sat.projection.SatProjection;
import fr.radi3nt.maths.sat.projection.SatProjectionProvider;

import java.util.Collection;

public interface VerticesSATShape extends SATShape {

    @Override
    default SatProjection project(SatProjectionProvider provider, SatAxis axis) {
        Vector3D[] vertices = getVertices();

        Vector3D minVertex = vertices[0];
        Vector3D maxVertex = vertices[0];
        double min = axis.dot(vertices[0]);
        double max = min;
        for (int i = 1; i < vertices.length; i++) {
            double p = axis.dot(vertices[i]);
            if (p < min) {
                min = p;
                minVertex = vertices[i];
            } else if (p > max) {
                max = p;
                maxVertex = vertices[i];
            }
        }

        return provider.project(minVertex, maxVertex, min, max);
    }

    Vector3D[] getVertices();

    ClipPlanes getClipPlanes();
    Collection<Edge> getClipEdges();
}
