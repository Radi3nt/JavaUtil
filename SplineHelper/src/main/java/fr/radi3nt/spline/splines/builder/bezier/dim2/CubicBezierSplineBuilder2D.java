package fr.radi3nt.spline.splines.builder.bezier.dim2;

import fr.radi3nt.spline.splines.builder.bezier.dim1.BezierPoint;
import fr.radi3nt.spline.splines.builder.bezier.dim1.CubicBezierSplineBuilder;
import fr.radi3nt.spline.splines.dimensions.EncapsulatingSpline2D;
import fr.radi3nt.spline.splines.dimensions.Spline2D;

public class CubicBezierSplineBuilder2D {

    private final CubicBezierSplineBuilder[] splineBuilders = new CubicBezierSplineBuilder[3];

    public CubicBezierSplineBuilder2D(BezierPoint2D... positions) {
        BezierPoint[] xPos = new BezierPoint[positions.length];
        BezierPoint[] yPos = new BezierPoint[positions.length];

        for (int i = 0; i < positions.length; i++) {
            BezierPoint2D position = positions[i];
            xPos[i] = position.getXAxis();
            yPos[i] = position.getYAxis();
        }

        splineBuilders[0] = new CubicBezierSplineBuilder(xPos);
        splineBuilders[1] = new CubicBezierSplineBuilder(yPos);
    }

    public Spline2D build() {
        return new EncapsulatingSpline2D(splineBuilders[0].build(), splineBuilders[1].build());
    }

    public CubicBezierSplineController2D controller() {
        return new CubicBezierSplineController2D(splineBuilders[0].controller(), splineBuilders[1].controller());
    }

}
