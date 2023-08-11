package fr.radi3nt.spline.splines.builder.cardinal.dim1;

import fr.radi3nt.spline.curve.curves.cardinal.CardinalCurveController;

public class DirectCardinalCurveController implements CardinalCurveController {

    private final float positionA;
    private final float positionB;
    private final float positionC;
    private final float positionD;
    private final float scale;

    public DirectCardinalCurveController(float positionA, float positionB, float positionC, float positionD, float scale) {
        this.positionA = positionA;
        this.positionB = positionB;
        this.positionC = positionC;
        this.positionD = positionD;
        this.scale = scale;
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

    @Override
    public float getScale() {
        return scale;
    }
}
