package fr.radi3nt.spline.splines.builder.bezier.dim3;

import fr.radi3nt.spline.splines.builder.bezier.dim1.CubicBezierSplineController;

public class CubicBezierSplineController3D {

    private final CubicBezierSplineController[] controllers;

    public CubicBezierSplineController3D(CubicBezierSplineController... controllers) {
        this.controllers = controllers;
    }


    public void setPosition(BezierPoint3D point, int index) {
        controllers[0].setPoint(point.getXAxis(), index);
        controllers[1].setPoint(point.getYAxis(), index);
        controllers[2].setPoint(point.getZAxis(), index);
    }
}
