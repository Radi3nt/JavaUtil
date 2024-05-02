package fr.radi3nt.spline.splines.builder.bspline.dim1;

import fr.radi3nt.spline.curve.curves.bcurve.BCurveController;

public class InferredBCurveController implements BCurveController {

    private final int index;
    private final float[] pos;

    public InferredBCurveController(int index, float[] pos) {
        this.index = index;
        this.pos = pos;
    }

    @Override
    public float getPositionA() {
        return index == 0 ? (mirror(pos[index], pos[index + 1])) : pos[index - 1];
    }

    @Override
    public float getPositionB() {
        return pos[index];
    }

    @Override
    public float getPositionC() {
        return index + 1 >= pos.length ? mirror(getPositionB(), getPositionA()) : pos[index + 1];
    }

    @Override
    public float getPositionD() {
        return index == pos.length - 2 ? mirror(getPositionC(), getPositionB()) : pos[index + 2];
    }

    private float mirror(float middlePos, float endPos) {
        return middlePos*2 - endPos;
    }
}
