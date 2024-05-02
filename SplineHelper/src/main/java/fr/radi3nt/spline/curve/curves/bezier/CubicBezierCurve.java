package fr.radi3nt.spline.curve.curves.bezier;

import fr.radi3nt.spline.curve.CharacteristicCurve;

public class CubicBezierCurve extends CharacteristicCurve {

    private final CubicBezierCurveController controller;

    public CubicBezierCurve(CubicBezierCurveController controller) {
        this.controller = controller;
    }

    protected float computeP(float a, float t, float t2, float t3) {

        float startPoint = controller.getStartPoint();
        float startPointControl = controller.getStartPointControl();

        float endPoint = controller.getEndPoint();
        float endPointControl = controller.getEndPointControl();

        return a * startPoint
                + t *(-3*startPoint+3*startPointControl)
                + t2 *(3*startPoint-6*startPointControl+3*endPointControl)
                + t3 *(-startPoint+3*startPointControl-3*endPointControl+endPoint);
    }

    public CubicBezierCurveController getController() {
        return controller;
    }
}
