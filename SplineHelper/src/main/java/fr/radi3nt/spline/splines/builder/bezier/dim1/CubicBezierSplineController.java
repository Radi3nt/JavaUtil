package fr.radi3nt.spline.splines.builder.bezier.dim1;

import fr.radi3nt.spline.curve.curves.bezier.CubicBezierCurveController;
import fr.radi3nt.spline.splines.builder.SplineController;

public class CubicBezierSplineController implements SplineController {

    private final BezierPoint[] positions;

    public CubicBezierSplineController(BezierPoint[] positions) {
        this.positions = positions;
    }

    public void setPoint(BezierPoint point, int index) {
        positions[index] = point;
    }

    public CubicBezierCurveController createForIndex(int index) {
        return new InferredCubicBezierCurveController(index, positions);
    }

}
