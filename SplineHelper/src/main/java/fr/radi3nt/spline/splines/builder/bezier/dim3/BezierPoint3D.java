package fr.radi3nt.spline.splines.builder.bezier.dim3;

import fr.radi3nt.spline.splines.builder.bezier.dim1.BezierPoint;

public class BezierPoint3D {

    private final BezierPoint xAxis;
    private final BezierPoint yAxis;
    private final BezierPoint zAxis;

    public BezierPoint3D(BezierPoint xAxis, BezierPoint yAxis, BezierPoint zAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.zAxis = zAxis;
    }

    public BezierPoint3D(float pointX, float pointControlBeforeX, float pointControlAfterX, float pointY, float pointControlBeforeY, float pointControlAfterY, float pointZ, float pointControlBeforeZ, float pointControlAfterZ) {
        this(new BezierPoint(pointX, pointControlBeforeX, pointControlAfterX), new BezierPoint(pointY, pointControlBeforeY, pointControlAfterY), new BezierPoint(pointZ, pointControlBeforeZ, pointControlAfterZ));
    }

    public BezierPoint getXAxis() {
        return xAxis;
    }

    public BezierPoint getYAxis() {
        return yAxis;
    }

    public BezierPoint getZAxis() {
        return zAxis;
    }
}
