package fr.radi3nt.spline.curve.curves;

import fr.radi3nt.spline.curve.CharacteristicCurve;

public class BCurve extends CharacteristicCurve {

    private final float pointA;
    private final float pointB;
    private final float pointC;
    private final float pointD;

    public BCurve(float pointA, float pointB, float pointC, float pointD) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.pointD = pointD;
    }

    @Override
    protected float computeP(float a, float t, float t2, float t3) {
        float p = a * (pointA + 4*pointB + pointC)
                + t *(-3*pointA + 3*pointC)
                + t2 *(3*pointA - 6*pointB + 3*pointC)
                + t3 *(-pointA + 3*pointB - 3*pointC + pointD);
        return p/6;
    }
}
