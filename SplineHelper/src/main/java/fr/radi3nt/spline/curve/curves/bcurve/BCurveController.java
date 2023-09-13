package fr.radi3nt.spline.curve.curves.bcurve;

import fr.radi3nt.spline.curve.controller.CurveController;

public interface BCurveController extends CurveController {

    float getPositionA();
    float getPositionB();
    float getPositionC();
    float getPositionD();

}
