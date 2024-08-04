package fr.radi3nt.spline.splines.builder.bezier.dim3;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.spline.splines.builder.bezier.dim1.BezierPoint;
import fr.radi3nt.spline.splines.builder.bezier.dim1.CubicBezierSplineBuilder;
import fr.radi3nt.spline.splines.dimensions.EncapsulatingSpline3D;
import fr.radi3nt.spline.splines.dimensions.Spline3D;

public class CubicBezierSplineBuilder3D {

    private final CubicBezierSplineBuilder[] splineBuilders = new CubicBezierSplineBuilder[3];

    public static CubicBezierSplineBuilder3D fromTwoPoints(Vector3f pointA, Vector3f dirA, Vector3f pointB, Vector3f dirB) {
        return new CubicBezierSplineBuilder3D(
                new BezierPoint3D(pointA.getX(), 0, pointA.getX()+dirA.getX(), pointA.getY(), 0, pointA.getY()+dirA.getY(), pointA.getZ(), 0, pointA.getZ()+dirA.getZ()),
                new BezierPoint3D(pointB.getX(), pointB.getX()+dirB.getX(), 0, pointB.getY(), pointB.getY()+dirB.getY(), 0, pointB.getZ(), pointB.getZ()+dirB.getZ(), 0)
        );
    }

    public CubicBezierSplineBuilder3D(BezierPoint3D... positions) {
        BezierPoint[] xPos = new BezierPoint[positions.length];
        BezierPoint[] yPos = new BezierPoint[positions.length];
        BezierPoint[] zPos = new BezierPoint[positions.length];

        for (int i = 0; i < positions.length; i++) {
            BezierPoint3D position = positions[i];
            xPos[i] = position.getXAxis();
            yPos[i] = position.getYAxis();
            zPos[i] = position.getZAxis();
        }

        splineBuilders[0] = new CubicBezierSplineBuilder(xPos);
        splineBuilders[1] = new CubicBezierSplineBuilder(yPos);
        splineBuilders[2] = new CubicBezierSplineBuilder(zPos);
    }

    public Spline3D build() {
        return new EncapsulatingSpline3D(splineBuilders[0].build(), splineBuilders[1].build(), splineBuilders[2].build());
    }

    public CubicBezierSplineController3D controller() {
        return new CubicBezierSplineController3D(splineBuilders[0].controller(), splineBuilders[1].controller(), splineBuilders[2].controller());
    }

}
