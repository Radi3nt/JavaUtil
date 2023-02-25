package fr.radi3nt.maths.sat.shape;

import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.sat.components.SatAxis;
import fr.radi3nt.maths.sat.projection.SatProjection;
import fr.radi3nt.maths.sat.projection.SatProjectionProvider;

public interface VerticesSATShape extends SATShape {

    @Override
    default SatProjection project(SatProjectionProvider provider, SatAxis axis) {
        Vector3D[] vertices = getVertices();

        double min = axis.dot(vertices[0]);
        double max = min;
        for (int i = 1; i < vertices.length; i++) {
            double p = axis.dot(vertices[i]);
            if (p < min) {
                min = p;
            } else if (p > max) {
                max = p;
            }
        }

        return provider.project(min, max);
    }

    Vector3D[] getVertices();
}
