package fr.radi3nt.spline;

import fr.radi3nt.spline.curve.Curve;
import fr.radi3nt.spline.curve.curves.cardinal.CardinalCurve;
import fr.radi3nt.spline.curve.curves.cardinal.CatmullRomCurve;
import fr.radi3nt.spline.splines.Spline;
import fr.radi3nt.spline.splines.builder.cardinal.dim1.DirectCardinalCurveController;

public class MainSplineTesting {

    public static void main(String[] args) {
        //testCurves();
    }

    private static void printSpline(Spline spline, int times) {
        for (int i = 0; i <= 10*times; i++) {
            System.out.println(spline.interpolate(i / 10f));
        }
        System.out.println("--");
    }

    private static void testCurves() {
        Curve curve = new CardinalCurve(new DirectCardinalCurveController(0, 1, 2, 3, 0.5f));
        printCurve(curve, 0);
        curve = new CatmullRomCurve(0, 1, 2, 3);
        printCurve(curve, 1);
    }

    private static void printCurve(Curve curve, int index) {
        for (int i = 0; i <= 10; i++) {
            System.out.println(curve.interpolate(i / 10f+index));
        }
        System.out.println("--");
    }

}
