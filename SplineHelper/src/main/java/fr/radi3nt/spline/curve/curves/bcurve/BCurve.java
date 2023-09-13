package fr.radi3nt.spline.curve.curves.bcurve;

import fr.radi3nt.spline.curve.CharacteristicCurve;

public class BCurve extends CharacteristicCurve {

    private final BCurveController controller;

    public BCurve(BCurveController controller) {
        this.controller = controller;
    }

    @Override
    protected float computeP(float a, float t, float t2, float t3) {
        float pointA = controller.getPositionA();
        float pointB = controller.getPositionB();
        float pointC = controller.getPositionC();
        float pointD = controller.getPositionD();
        float p = a * (pointA + 4*pointB + pointC)
                + t *(-3*pointA + 3*pointC)
                + t2 *(3*pointA - 6*pointB + 3*pointC)
                + t3 *(-pointA + 3*pointB - 3*pointC + pointD);
        return p/6;
    }

    public BCurveController getController() {
        return controller;
    }
}
