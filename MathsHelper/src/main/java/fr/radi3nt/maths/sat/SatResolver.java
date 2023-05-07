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

    private SatAxis msa = null;
    private double penetration = Double.MAX_VALUE;

    private int normal = 1;

    public SatResolver(SatProjectionProvider provider, SATShape shape1, SATShape shape2) {
        this.provider0 = provider;
        this.provider1 = provider.duplicate();
        this.shape1 = shape1;
        this.shape2 = shape2;
    }

    public boolean resolve() {
        normal = 1;
        penetration = Double.MAX_VALUE;
        msa = null;

        SatAxis[] axes1 = shape1.getAxes();
        SatAxis[] axes2 = shape2.getAxes();
        // loop over the axes1
        if (testOverlapOnAxes(axes1)) return false;
        // loop over the axes2
        if (testOverlapOnAxes(axes2)) return false;

        SatAxis[] edgeAxis = computeAxesFromEdges(shape1.getEdges(), shape2.getEdges());
        return !testOverlapOnAxes(edgeAxis);
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
            if (axis.getNormal().lengthSquared() == 0 || Double.isNaN(axis.getNormal().lengthSquared()))
                continue;
            // project both shapes onto the axis
            SatProjection p1 = shape1.project(provider0, axis);
            SatProjection p2 = shape2.project(provider1, axis);
            // do the projections overlap?
            if (p1.noOverlap(p2)) {
                // then we can guarantee that the shapes do not overlap
                return true;
            } else {
                double signedPenetration = p1.getOverlap(p2);
                double penetration = Math.abs(signedPenetration);
                if (this.penetration > penetration) {
                    msa = axis;
                    this.penetration = penetration;
                    normal = p1.normal(p2);
                }
            }
        }

        return false;
    }

    public double getPenetration() {
        return penetration;
    }

    public SatAxis getMsa() {
        return msa;
    }

    public int getNormal() {
        return normal;
    }
}
