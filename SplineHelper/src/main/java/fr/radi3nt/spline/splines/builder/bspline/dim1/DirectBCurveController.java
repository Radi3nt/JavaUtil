package fr.radi3nt.spline.splines.builder.bspline.dim1;

import fr.radi3nt.spline.curve.curves.bcurve.BCurveController;
import fr.radi3nt.spline.curve.curves.cardinal.CardinalCurveController;

public class DirectBCurveController implements BCurveController {

    private final float positionA;
    private final float positionB;
    private final float positionC;
    private final float positionD;

    public DirectBCurveController(float positionA, float positionB, float positionC, float positionD) {
        this.positionA = positionA;
        this.positionB = positionB;
        this.positionC = positionC;
        this.positionD = positionD;
    }

    @Override
    public float getPositionA() {
        return positionA;
    }

    @Override
    public float getPositionB() {
        return positionB;
    }

    @Override
    public float getPositionC() {
        return positionC;
    }

    @Override
    public float getPositionD() {
        return positionD;
    }
}
