package fr.radi3nt.maths.sat.swept;

import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.sat.components.SatAxis;
import fr.radi3nt.maths.sat.components.SatEdge;
import fr.radi3nt.maths.sat.projection.SatProjection;
import fr.radi3nt.maths.sat.projection.SatProjectionProvider;
import fr.radi3nt.maths.sat.shape.SATShape;

public class SweptSatResolver {

    private final SatProjectionProvider provider0;
    private final SatProjectionProvider provider1;

    private final SATShape shape1;
    private final SATShape shape2;

    private final Vector3D velocity;

    public SweptSatResolver(SatProjectionProvider provider, SATShape shape1, SATShape shape2, Vector3D velocity) {
        this.provider0 = provider;
        this.provider1 = provider.duplicate();
        this.shape1 = shape1;
        this.shape2 = shape2;
        this.velocity = velocity;
    }

    public SweptResult resolve() {
        SatAxis[] axes1 = shape1.getAxes();
        SatAxis[] axes2 = shape2.getAxes();

        SweptResult shapeOne = testOverlapOnAxes(axes1);
        SweptResult shapeTwo = testOverlapOnAxes(axes2);

        SatAxis[] edgeAxis = computeAxesFromEdges(shape1.getEdges(), shape2.getEdges());
        SweptResult edges = testOverlapOnAxes(edgeAxis);

        double tEnter = -Double.MAX_VALUE;
        double tLeave = Double.MAX_VALUE;
        SatAxis enterAxis = null;

        if (tEnter < shapeOne.getTEnter()) {
            enterAxis = shapeOne.getEnterAxis();
            tEnter = shapeOne.getTEnter();
        }

        tLeave = Math.min(shapeOne.getTLeave(), tLeave);

        if (tEnter < shapeTwo.getTEnter()) {
            enterAxis = shapeTwo.getEnterAxis();
            tEnter = shapeTwo.getTEnter();
        }
        tLeave = Math.min(shapeTwo.getTLeave(), tLeave);

        if (tEnter < edges.getTEnter()) {
            enterAxis = edges.getEnterAxis();
            tEnter = edges.getTEnter();
        }
        tLeave = Math.min(edges.getTLeave(), tLeave);

        if (tEnter > tLeave) {
            return SweptResult.NO_CONTACT;
        }

        return new SweptResult(true, tEnter, tLeave, enterAxis);
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

    private SweptResult testOverlapOnAxes(SatAxis... axes) {

        double tEnter = -Double.MAX_VALUE;
        double tLeave = Double.MAX_VALUE;
        SatAxis enterAxis = null;

        for (SatAxis axis : axes) {
            SatProjection p1 = shape1.project(provider0, axis);
            SatProjection p2 = shape2.project(provider1, axis);

            double speed = axis.dot(velocity);
            if (speed == 0) {
                if (p1.noOverlap(p2)) {
                    tEnter = Double.MAX_VALUE;
                    tLeave = -Double.MAX_VALUE;
                }
                continue;
            }

            p1.sweptOverlap(p2, speed);
            if (tEnter < p1.getTEnter()) {
                enterAxis = axis;
                tEnter = p1.getTEnter();
            }
            tLeave = Math.min(p1.getTLeave(), tLeave);
        }

        return new SweptResult(true, tEnter, tLeave, enterAxis);
    }
}
