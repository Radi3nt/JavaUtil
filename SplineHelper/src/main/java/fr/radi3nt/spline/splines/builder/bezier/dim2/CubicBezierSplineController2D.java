package fr.radi3nt.spline.splines.builder.bezier.dim2;

import fr.radi3nt.spline.splines.builder.bezier.dim1.CubicBezierSplineController;

public class CubicBezierSplineController2D {

    private final CubicBezierSplineController[] controllers;

    public CubicBezierSplineController2D(CubicBezierSplineController... controllers) {
        this.controllers = controllers;
    }


    public void setPosition(BezierPoint2D point, int index) {
        controllers[0].setPoint(point.getXAxis(), index);
        controllers[1].setPoint(point.getYAxis(), index);
    }
}
