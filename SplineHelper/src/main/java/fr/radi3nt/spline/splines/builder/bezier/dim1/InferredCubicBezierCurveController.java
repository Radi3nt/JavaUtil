package fr.radi3nt.spline.splines.builder.bezier.dim1;

public class InferredCubicBezierCurveController implements CubicBezierCurveController {

    private final int index;

    private final BezierPoint[] pos;

    public InferredCubicBezierCurveController(int index, BezierPoint[] pos) {
        this.index = index;
        this.pos = pos;
    }

    @Override
    public float getStartPoint() {
        return pos[index].getPoint();
    }

    @Override
    public float getStartPointControl() {
        return pos[index].getPointControlAfter();
    }

    @Override
    public float getEndPoint() {
        return pos[index+1].getPoint();
    }

    @Override
    public float getEndPointControl() {
        return pos[index+1].getPointControlBefore();
    }
}
