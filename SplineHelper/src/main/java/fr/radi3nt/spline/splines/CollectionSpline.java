package fr.radi3nt.spline.splines;

import fr.radi3nt.spline.curve.Curve;

import java.util.List;

public class CollectionSpline implements Spline {

    protected final List<Curve> curves;

    public CollectionSpline(List<Curve> curves) {
        this.curves = curves;
    }

    @Override
    public float interpolate(float t) {
        int index = (int) Math.nextDown(t);
        Curve curve = getCurveAtIndex(index);
        return curve.interpolate(t-index);
    }

    @Override
    public float velocity(float t) {
        int index = (int) Math.nextDown(t)-1;
        Curve curve = getCurveAtIndex(index);
        return curve.velocity(t-index);
    }

    protected Curve getCurveAtIndex(int index) {
        checkForValidIndex(index);
        return curves.get(index);
    }

    private void checkForValidIndex(int index) {
        if (index<0 || index>= curves.size())
            throw new IllegalArgumentException("Index is not in bounds 0 < " + curves.size() + " (index being: " + index + ")");
    }
}
