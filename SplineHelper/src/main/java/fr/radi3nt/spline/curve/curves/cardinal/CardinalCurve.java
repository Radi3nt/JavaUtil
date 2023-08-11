package fr.radi3nt.spline.curve.curves.cardinal;

import fr.radi3nt.spline.curve.CharacteristicCurve;

public class CardinalCurve extends CharacteristicCurve {

    private final CardinalCurveController cardinalCurveController;

    public CardinalCurve(CardinalCurveController cardinalCurveController) {
        this.cardinalCurveController = cardinalCurveController;
    }

    @Override
    protected float computeP(float a, float t, float t2, float t3) {

        float pointA = cardinalCurveController.getPositionA();
        float pointB = cardinalCurveController.getPositionB();
        float pointC = cardinalCurveController.getPositionC();
        float pointD = cardinalCurveController.getPositionD();
        float scale = cardinalCurveController.getScale();

        return a * pointB
                + t *(-pointA*scale + pointC*scale)
                + t2 *(2*pointA*scale + (scale-3)*pointB + (3-2*scale)*pointC - pointD*scale)
                + t3 *(-pointA*scale + (2-scale)*pointB + (scale-2)*pointC + pointD*scale);
    }
}
