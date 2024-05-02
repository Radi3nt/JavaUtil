package fr.radi3nt.spline.splines.builder.bspline.dim3;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.spline.splines.builder.bspline.dim1.BSplineController;

public class BSplineController3D {

    private final BSplineController[] controllers;

    public BSplineController3D(BSplineController... controllers) {
        this.controllers = controllers;
    }

    public void setPosition(int index, Vector3f position) {
        controllers[0].setPosition(index, position.getX());
        controllers[1].setPosition(index, position.getY());
        controllers[2].setPosition(index, position.getZ());
    }
}
