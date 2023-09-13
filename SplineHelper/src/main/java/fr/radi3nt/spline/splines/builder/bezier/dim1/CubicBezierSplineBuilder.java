package fr.radi3nt.spline.splines.builder.bezier.dim1;

import fr.radi3nt.spline.curve.curves.bezier.CubicBezierCurve;
import fr.radi3nt.spline.splines.CollectionSpline;
import fr.radi3nt.spline.splines.Spline;
import fr.radi3nt.spline.splines.builder.SplineBuilder;

import java.util.ArrayList;
import java.util.List;

public class CubicBezierSplineBuilder implements SplineBuilder {

    private final CollectionSpline spline;
    private final CubicBezierSplineController controller;

    public CubicBezierSplineBuilder(BezierPoint[] pos) {
        controller = new CubicBezierSplineController(pos);
        List<CubicBezierCurve> curves = new ArrayList<>();
        for (int i = 0; i < pos.length-1; i++) {
            CubicBezierCurve cardinal = new CubicBezierCurve(controller.createForIndex(i));
            curves.add(cardinal);
        }
        spline = new CollectionSpline(curves);
    }

    @Override
    public Spline build() {
        return spline;
    }

    public CubicBezierSplineController controller() {
        return controller;
    }
}