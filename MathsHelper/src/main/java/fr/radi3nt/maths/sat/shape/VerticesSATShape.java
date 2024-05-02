package fr.radi3nt.maths.sat.shape;

import fr.radi3nt.maths.aabb.AABB;
import fr.radi3nt.maths.aabb.AxisMapping;
import fr.radi3nt.maths.aabb.SetAABB;
import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.sat.clip.ClipPlanes;
import fr.radi3nt.maths.sat.clip.Edge;
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
            min = Math.min(p, min);
            max = Math.max(p, max);
        }

        return provider.project(min, max);
    }

    Vector3D[] getVertices();

    ClipPlanes getClipPlanes();
    Edge[] getClipEdges();

    default AABB toAABB() {
        final AABB aabb;
        float maxX = -Float.MAX_VALUE;
        float minX = Float.MAX_VALUE;

        float maxY = -Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;

        float maxZ = -Float.MAX_VALUE;
        float minZ = Float.MAX_VALUE;

        for (Vector3D vertex : getVertices()) {
            maxX = (float) Math.max(maxX, vertex.getX());
            minX = (float) Math.min(minX, vertex.getX());

            maxY = (float) Math.max(maxY, vertex.getY());
            minY = (float) Math.min(minY, vertex.getY());

            maxZ = (float) Math.max(maxZ, vertex.getZ());
            minZ = (float) Math.min(minZ, vertex.getZ());
        }

        aabb = new SetAABB(
                new AxisMapping(minX, maxX),
                new AxisMapping(minY, maxY),
                new AxisMapping(minZ, maxZ));
        return aabb;
    }
}
