package fr.radi3nt.spline.splines.builder.bezier.dim2;

import fr.radi3nt.spline.splines.builder.bezier.dim1.BezierPoint;

public class BezierPoint2D {

    private final BezierPoint xAxis;
    private final BezierPoint yAxis;

    public BezierPoint2D(BezierPoint xAxis, BezierPoint yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public BezierPoint2D(float pointX, float pointControlBeforeX, float pointControlAfterX, float pointY, float pointControlBeforeY, float pointControlAfterY) {
        this(new BezierPoint(pointX, pointControlBeforeX, pointControlAfterX), new BezierPoint(pointY, pointControlBeforeY, pointControlAfterY));
    }

    public BezierPoint getXAxis() {
        return xAxis;
    }

    public BezierPoint getYAxis() {
        return yAxis;
    }
}
