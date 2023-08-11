package fr.radi3nt.spline.curve.curves.cardinal;

import fr.radi3nt.spline.curve.controller.CurveController;

public interface CardinalCurveController extends CurveController {

    float getPositionA();
    float getPositionB();
    float getPositionC();
    float getPositionD();

    float getScale();

}
