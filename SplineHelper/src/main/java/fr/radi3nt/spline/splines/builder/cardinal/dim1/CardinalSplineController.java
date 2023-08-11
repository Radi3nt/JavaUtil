package fr.radi3nt.spline.splines.builder.cardinal.dim1;

import fr.radi3nt.spline.curve.curves.cardinal.CardinalCurveController;
import fr.radi3nt.spline.splines.builder.PositionnedSplineController;

import java.util.concurrent.atomic.AtomicReference;

public class CardinalSplineController implements PositionnedSplineController {


    private final float[] positions;
    private final AtomicReference<Float> scale;

    public CardinalSplineController(float[] positions, float scale) {
        this.positions = positions;
        this.scale = new AtomicReference<>(scale);
    }

    @Override
    public void setPosition(int index, float pos) {
        positions[index] = pos;
    }

    public void setScale(float scale) {
        this.scale.set(scale);
    }

    public CardinalCurveController createForIndex(int index) {
        return new InferredCardinalCurveController(index, positions, scale);
    }

}
