package fr.radi3nt.spline.curve.curves.cardinal;

import fr.radi3nt.spline.curve.CharacteristicCurve;

public class CardinalCurve extends CharacteristicCurve {

    private final float pointA;
    private final float pointB;
    private final float pointC;
    private final float pointD;

    private final float scale;

    public CardinalCurve(float pointA, float pointB, float pointC, float pointD, float scale) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.pointD = pointD;
        this.scale = scale;
    }

    @Override
    protected float computeP(float a, float t, float t2, float t3) {
        return a * pointB
                + t *(-pointA*scale + pointC*scale)
                + t2 *(2*pointA*scale + (scale-3)*pointB + (3-2*scale)*pointC - pointD*scale)
                + t3 *(-pointA*scale + (2-scale)*pointB + (scale-2)*pointC + pointD*scale);
    }
}
