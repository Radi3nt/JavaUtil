package fr.radi3nt.maths.sat.swept;

import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.sat.components.SatAxis;
import fr.radi3nt.maths.sat.components.SatEdge;
import fr.radi3nt.maths.sat.projection.SatProjection;
import fr.radi3nt.maths.sat.projection.SatProjectionProvider;
import fr.radi3nt.maths.sat.shape.SATShape;

public class CheepSweptSatResolver {

    private final SatProjectionProvider provider0;
    private final SatProjectionProvider provider1;
    private SATShape shape1;
    private SATShape shape2;

    private Vector3D velocity;
    private final Vector3D cacheCross = new Vector3D();
    private final Vector3D cacheCrossResult = new Vector3D();

    public CheepSweptSatResolver(SatProjectionProvider provider) {
        this.provider0 = provider;
        this.provider1 = provider.duplicate();
    }

    public void set(SATShape shape1, SATShape shape2, Vector3D velocity) {
        this.shape1 = shape1;
        this.shape2 = shape2;
        this.velocity = velocity;
    }

    public SweptResult resolve() {
        SatAxis[] axes1 = shape1.getAxes();
        SatAxis[] axes2 = shape2.getAxes();
        // loop over the axes1

        SweptResult shapeOne = testOverlapOnAxes(axes1);
        if (shapeOne == null)
            return new SweptResult(Double.MAX_VALUE, -Double.MAX_VALUE, null);
        // loop over the axes2
        SweptResult shapeTwo = testOverlapOnAxes(axes2);
        if (shapeTwo == null)
            return new SweptResult(Double.MAX_VALUE, -Double.MAX_VALUE, null);

        SweptResult edges = testOverlapOnAxes(shape1.getEdges(), shape2.getEdges());
        if (edges == null)
            return new SweptResult(Double.MAX_VALUE, -Double.MAX_VALUE, null);

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
            return new SweptResult(Double.MAX_VALUE, -Double.MAX_VALUE, null);
        }

        return new SweptResult(tEnter, tLeave, enterAxis);
    }

    private SweptResult testOverlapOnAxes(SatEdge[] edges, SatEdge[] others) {
        double tEnter = -Double.MAX_VALUE;
        double tLeave = Double.MAX_VALUE;
        SatAxis enterAxis = null;

        SweptResult resultPassing = new SweptResult();

        for (SatEdge edge : edges) {
            for (SatEdge other : others) {
                SatAxis axis = edge.axis(other, cacheCross);

                // project both shapes onto the axis
                SatProjection p1 = shape1.project(provider0, axis);
                SatProjection p2 = shape2.project(provider1, axis);
                // do the projections overlap?

                double speed = axis.dot(velocity);
                if (speed == 0) {
                    if (p1.noOverlap(p2)) {
                        return null;
                    }
                    continue;
                }

                p1.sweptOverlap(p2, speed, resultPassing);
                if (tEnter < resultPassing.getTEnter()) {
                    enterAxis = axis.useNewNormalVector(cacheCrossResult);
                    tEnter = resultPassing.getTEnter();
                    if (tEnter > 1)
                        return null;
                }
                tLeave = Math.min(resultPassing.getTLeave(), tLeave);
                if (tLeave < 0)
                    return null;
            }
        }

        return new SweptResult(tEnter, tLeave, enterAxis);
    }

    private SweptResult testOverlapOnAxes(SatAxis... axes) {

        double tEnter = -Double.MAX_VALUE;
        double tLeave = Double.MAX_VALUE;
        SatAxis enterAxis = null;

        SweptResult resultPassing = new SweptResult();
        for (SatAxis axis : axes) {
            // project both shapes onto the axis
            SatProjection p1 = shape1.project(provider0, axis);
            SatProjection p2 = shape2.project(provider1, axis);
            // do the projections overlap?

            double speed = axis.dot(velocity);
            if (speed == 0) {
                if (p1.noOverlap(p2)) {
                    return null;
                }
                continue;
            }

            p1.sweptOverlap(p2, speed, resultPassing);
            if (tEnter < resultPassing.getTEnter()) {
                enterAxis = axis;
                tEnter = resultPassing.getTEnter();
                if (tEnter > 1)
                    return null;
            }
            tLeave = Math.min(resultPassing.getTLeave(), tLeave);
            if (tLeave < 0)
                return null;
        }

        return new SweptResult(tEnter, tLeave, enterAxis);
    }
}
