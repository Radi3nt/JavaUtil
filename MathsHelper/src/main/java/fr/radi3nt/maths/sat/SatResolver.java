package fr.radi3nt.maths.sat;

import fr.radi3nt.maths.sat.components.SatAxis;
import fr.radi3nt.maths.sat.components.SatEdge;
import fr.radi3nt.maths.sat.projection.SatProjection;
import fr.radi3nt.maths.sat.projection.SatProjectionProvider;
import fr.radi3nt.maths.sat.shape.SATShape;

public class SatResolver {

    private final SatProjectionProvider provider0;
    private final SatProjectionProvider provider1;
    private final SATShape shape1;
    private final SATShape shape2;

    public SatResolver(SatProjectionProvider provider, SATShape shape1, SATShape shape2) {
        this.provider0 = provider;
        this.provider1 = provider.duplicate();
        this.shape1 = shape1;
        this.shape2 = shape2;
    }

    public boolean resolve() {
        SatAxis[] axes1 = shape1.getAxes();
        SatAxis[] axes2 = shape2.getAxes();
        // loop over the axes1
        if (testOverlapOnAxes(axes1)) return false;
        // loop over the axes2
        if (testOverlapOnAxes(axes2)) return false;

        SatAxis[] edgeAxis = computeAxesFromEdges(shape1.getEdges(), shape2.getEdges());
        return !testOverlapOnAxes(edgeAxis);

        // if we get here then we know that every axis had overlap on it, so we can guarantee an intersection
    }

    private SatAxis[] computeAxesFromEdges(SatEdge[] edges, SatEdge[] others) {
        int size = edges.length * others.length;
        SatAxis[] axes = new SatAxis[size];
        int i = 0;
        for (SatEdge edge : edges) {
            for (SatEdge other : others) {
                axes[i] = edge.axis(other);
                i++;
            }
        }
        return axes;
    }

    private boolean testOverlapOnAxes(SatAxis[] axes) {
        for (SatAxis axis : axes) {
            // project both shapes onto the axis
            SatProjection p1 = shape1.project(provider0, axis);
            SatProjection p2 = shape2.project(provider1, axis);
            // do the projections overlap?
            if (p1.noOverlap(p2)) {
                // then we can guarantee that the shapes do not overlap
                return true;
            }
        }
        return false;
    }
}
