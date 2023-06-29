package fr.radi3nt.spline.splines.cardinal;

import fr.radi3nt.spline.curve.Curve;
import fr.radi3nt.spline.curve.curves.cardinal.CatmullRomCurve;

public class CatmullRomSpline extends CardinalSpline {

    private static final float CATMULL_ROM_FACTOR = 0.5f;

    public CatmullRomSpline(float[] pos) {
        super(pos, CATMULL_ROM_FACTOR);
    }

    protected Curve createCurve(float scale, float positionA, float positionB, float positionC, float positionD) {
        return new CatmullRomCurve(positionA, positionB, positionC, positionD);
    }
}
