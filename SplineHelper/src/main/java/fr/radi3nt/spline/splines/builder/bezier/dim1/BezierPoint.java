package fr.radi3nt.spline.splines.builder.bezier.dim1;

public class BezierPoint {

    private final float point;
    private final float pointControlBefore;
    private final float pointControlAfter;

    public BezierPoint(float point, float pointControlBefore, float pointControlAfter) {
        this.point = point;
        this.pointControlBefore = pointControlBefore;
        this.pointControlAfter = pointControlAfter;
    }

    public float getPoint() {
        return point;
    }

    public float getPointControlBefore() {
        return pointControlBefore;
    }

    public float getPointControlAfter() {
        return pointControlAfter;
    }
}
