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

    public SatResolver(SatProjectionProvider provider, SATShape shape1, SATShape shape2) {
        this.provider0 = provider;
        this.provider1 = provider.duplicate();
        this.shape1 = shape1;
        this.shape2 = shape2;
    }

    public boolean resolve() {
        penetration = Double.MAX_VALUE;
        msa = null;

        SatAxis[] axes1 = shape1.getAxes();
        SatAxis[] axes2 = shape2.getAxes();
        // loop over the axes1
        if (testOverlapOnAxes(axes1, -1, false)) return false;
        // loop over the axes2
        if (testOverlapOnAxes(axes2, 1, false)) return false;

        SatAxis[] edgeAxis = computeAxesFromEdges(shape1.getEdges(), shape2.getEdges());
        return !testOverlapOnAxes(edgeAxis, 1, false);
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

    private boolean testOverlapOnAxes(SatAxis[] axes, int normal, boolean edge) {
        for (SatAxis axis : axes) {
            if (axis.getNormal().lengthSquared() == 0 || Double.isNaN(axis.getNormal().lengthSquared()))
                continue;

            SatProjection p1 = shape1.project(provider0, axis);
            SatProjection p2 = shape2.project(provider1, axis);

            if (!p1.noOverlap(p2)) {
                double signedPenetration = p1.getOverlap(p2);
                double penetration = Math.abs(signedPenetration);
                if (this.penetration > penetration) {
                    this.msa = axis;
                    this.penetration = penetration;
                }
            } else {
                return true;
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
}
