package fr.radi3nt.spline.splines.builder.bspline.dim1;

import fr.radi3nt.spline.curve.curves.bcurve.BCurve;
import fr.radi3nt.spline.splines.CollectionSpline;
import fr.radi3nt.spline.splines.Spline;
import fr.radi3nt.spline.splines.builder.SplineBuilder;

import java.util.ArrayList;
import java.util.List;

public class BSplineBuilder implements SplineBuilder {

    private final CollectionSpline spline;
    private final BSplineController controller;

    public BSplineBuilder(float[] pos) {
        controller = new BSplineController(pos);
        List<BCurve> curves = new ArrayList<>();
        for (int i = 0; i < pos.length-1; i++) {
            BCurve cardinal = new BCurve(controller.createForIndex(i));
            curves.add(cardinal);
        }
        spline = new CollectionSpline(curves);
    }

    @Override
    public Spline build() {
        return spline;
    }

    public BSplineController controller() {
        return controller;
    }
}
