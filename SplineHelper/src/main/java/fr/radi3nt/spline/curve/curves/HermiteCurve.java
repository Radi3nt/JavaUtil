package fr.radi3nt.spline.curve.curves;

import fr.radi3nt.spline.curve.CharacteristicCurve;
import fr.radi3nt.spline.curve.curves.bezier.CubicBezierCurve;
import fr.radi3nt.spline.splines.builder.bezier.dim1.DirectCubicBezierCurveController;

public class HermiteCurve extends CharacteristicCurve {

    private final float startPoint;
    private final float endPoint;

    private final float velocityStart;
    private final float velocityEnd;

    public HermiteCurve(float startPoint, float endPoint, float velocityStart, float velocityEnd) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.velocityStart = velocityStart;
        this.velocityEnd = velocityEnd;
    }

    public CubicBezierCurve toBezier() {
        return new CubicBezierCurve(new DirectCubicBezierCurveController(startPoint, endPoint, startPoint+velocityStart/3, endPoint-velocityEnd/3));
    }

    protected float computeP(float a, float t, float t2, float t3) {
        return a * startPoint
                + t * (velocityStart)
                + t2 * (-3*startPoint - 2*velocityStart + 3*endPoint - velocityEnd)
                + t3 * (2*startPoint + velocityStart - 2*endPoint + velocityEnd);
    }
}
