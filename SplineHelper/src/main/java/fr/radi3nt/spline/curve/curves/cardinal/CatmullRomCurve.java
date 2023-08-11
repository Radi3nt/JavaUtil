package fr.radi3nt.spline.curve.curves.cardinal;

import fr.radi3nt.spline.curve.CharacteristicCurve;

public class CatmullRomCurve extends CharacteristicCurve {

    private final float pointA;
    private final float pointB;
    private final float pointC;
    private final float pointD;

    public CatmullRomCurve(float pointA, float pointB, float pointC, float pointD) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.pointD = pointD;
    }

    @Override
    protected float computeP(float a, float t, float t2, float t3) {
        float p = a * 2 * pointB
                + t *(-pointA+pointC)
                + t2 *(2*pointA-5*pointB+4*pointC-pointD)
                + t3 *(-pointA+3*pointB-3*pointC+pointD);
        return p/2;
    }
}
