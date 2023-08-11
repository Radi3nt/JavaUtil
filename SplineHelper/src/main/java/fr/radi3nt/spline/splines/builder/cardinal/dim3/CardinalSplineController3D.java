package fr.radi3nt.spline.splines.builder.cardinal.dim3;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.spline.splines.builder.cardinal.dim1.CardinalSplineController;

public class CardinalSplineController3D {

    private final CardinalSplineController[] controllers;

    public CardinalSplineController3D(CardinalSplineController... controllers) {
        this.controllers = controllers;
    }


    public void setPosition(int index, Vector3f position) {
        controllers[0].setPosition(index, position.getX());
        controllers[1].setPosition(index, position.getY());
        controllers[2].setPosition(index, position.getZ());
    }

    public void setScale(float scale) {
        for (CardinalSplineController controller : controllers) {
            controller.setScale(scale);
        }
    }
}
