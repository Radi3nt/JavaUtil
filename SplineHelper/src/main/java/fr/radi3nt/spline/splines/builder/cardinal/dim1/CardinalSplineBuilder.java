package fr.radi3nt.spline.splines.builder.cardinal.dim1;

import fr.radi3nt.spline.curve.curves.cardinal.CardinalCurve;
import fr.radi3nt.spline.splines.CollectionSpline;
import fr.radi3nt.spline.splines.Spline;
import fr.radi3nt.spline.splines.builder.SplineBuilder;

import java.util.ArrayList;
import java.util.List;

public class CardinalSplineBuilder implements SplineBuilder {

    private final CollectionSpline spline;
    private final CardinalSplineController controller;

    public CardinalSplineBuilder(float[] pos, float scale) {
        controller = new CardinalSplineController(pos, scale);
        List<CardinalCurve> curves = new ArrayList<>();
        for (int i = 0; i < pos.length-1; i++) {
            CardinalCurve cardinal = new CardinalCurve(controller.createForIndex(i));
            curves.add(cardinal);
        }
        spline = new CollectionSpline(curves);
    }

    @Override
    public Spline build() {
        return spline;
    }

    public CardinalSplineController controller() {
        return controller;
    }
}
