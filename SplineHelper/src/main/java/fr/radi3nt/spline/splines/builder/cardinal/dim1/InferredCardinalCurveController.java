package fr.radi3nt.spline.splines.builder.cardinal.dim1;

import fr.radi3nt.spline.curve.curves.cardinal.CardinalCurveController;

import java.util.concurrent.atomic.AtomicReference;

public class InferredCardinalCurveController implements CardinalCurveController {

    private final int index;

    private final float[] pos;
    private final AtomicReference<Float> scale;

    public InferredCardinalCurveController(int index, float[] pos, AtomicReference<Float> scale) {
        this.index = index;
        this.pos = pos;
        this.scale = scale;
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

    @Override
    public float getScale() {
        return scale.get();
    }

    private float mirror(float middlePos, float endPos) {
        return middlePos*2 - endPos;
    }
}
