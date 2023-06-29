package fr.radi3nt.spline.curve.curves;

import fr.radi3nt.spline.curve.CharacteristicCurve;

public class CubicBezierCurve extends CharacteristicCurve {

    private final float startPoint;
    private final float endPoint;

    private final float startPointControl;
    private final float endPointControl;

    public CubicBezierCurve(float startPoint, float endPoint, float startPointControl, float endPointControl) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startPointControl = startPointControl;
        this.endPointControl = endPointControl;
    }

    protected float computeP(float a, float t, float t2, float t3) {
        return a * startPoint
                + t *(-3*startPoint+3*startPointControl)
                + t2 *(3*startPoint-6*startPointControl+3*endPointControl)
                + t3 *(-startPoint+3*startPointControl-3*endPointControl+endPoint);
    }
}
