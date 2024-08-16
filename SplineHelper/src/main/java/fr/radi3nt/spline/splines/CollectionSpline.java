package fr.radi3nt.spline.splines;

import fr.radi3nt.spline.curve.Curve;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionSpline implements Spline {

    protected final List<? extends Curve> curves;

    public CollectionSpline(List<? extends Curve> curves) {
        this.curves = curves;
    }

    @Override
    public float interpolate(float t) {
        int index = (int) Math.floor(t);
        if (t==curves.size())
            index = curves.size()-1;
        Curve curve = getCurveAtIndex(index);
        return curve.interpolate(t-index);
    }

    @Override
    public float velocity(float t) {
        int index = (int) Math.floor(t);
        if (t==curves.size())
            index = curves.size()-1;

        Curve curve = getCurveAtIndex(index);
        return curve.velocity(t-index);
    }

    @Override
    public int getSegmentCount() {
        return curves.size();
    }

    protected Curve getCurveAtIndex(int index) {
        checkForValidIndex(index);
        return curves.get(index);
    }

    private void checkForValidIndex(int index) {
        if (index<0 || index>= curves.size())
            throw new IllegalArgumentException("Index is not in bounds 0 < " + curves.size() + " (index being: " + index + ")");
    }

    public List<? extends Curve> getCurves() {
        return Collections.unmodifiableList(curves);
    }

    @Override
    public String toString() {
        return "CollectionSpline{" +
                "curves=" + Arrays.toString(curves.toArray(new Curve[0])) +
                '}';
    }
}
