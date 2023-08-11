package fr.radi3nt.spline.splines.builder.bezier.dim1;

public class DirectCubicBezierCurveController implements CubicBezierCurveController {

    private final float startPoint;
    private final float endPoint;

    private final float startPointControl;
    private final float endPointControl;

    public DirectCubicBezierCurveController(float startPoint, float endPoint, float startPointControl, float endPointControl) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startPointControl = startPointControl;
        this.endPointControl = endPointControl;
    }

    @Override
    public float getStartPoint() {
        return startPoint;
    }

    @Override
    public float getEndPoint() {
        return endPoint;
    }

    @Override
    public float getStartPointControl() {
        return startPointControl;
    }

    @Override
    public float getEndPointControl() {
        return endPointControl;
    }
}
