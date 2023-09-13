package fr.radi3nt.spline.splines.builder.bspline.dim1;

import fr.radi3nt.spline.curve.curves.bcurve.BCurveController;
import fr.radi3nt.spline.curve.curves.cardinal.CardinalCurveController;
import fr.radi3nt.spline.splines.builder.PositionnedSplineController;

public class BSplineController implements PositionnedSplineController {

    private final float[] positions;

    public BSplineController(float[] positions) {
        this.positions = positions;
    }

    @Override
    public void setPosition(int index, float pos) {
        positions[index] = pos;
    }

    public BCurveController createForIndex(int index) {
        return new InferredBCurveController(index, positions);
    }

}
