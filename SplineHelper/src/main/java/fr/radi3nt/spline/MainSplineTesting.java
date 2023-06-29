package fr.radi3nt.spline;

import fr.radi3nt.spline.curve.Curve;
import fr.radi3nt.spline.curve.curves.cardinal.CardinalCurve;
import fr.radi3nt.spline.curve.curves.cardinal.CatmullRomCurve;
import fr.radi3nt.spline.splines.Spline;
import fr.radi3nt.spline.splines.cardinal.CardinalSpline;
import fr.radi3nt.spline.splines.cardinal.CardinalUsingHermitSpline;
import fr.radi3nt.spline.splines.cardinal.CatmullRomSpline;

public class MainSplineTesting {

    public static void main(String[] args) {
        //testCurves();
        testSplines();
    }

    private static void testSplines() {
        Spline spline = new CardinalSpline(new float[] {0, 1, 2, 3}, 0.5f);
        printSpline(spline, 3);
        spline = new CatmullRomSpline(new float[] {0, 1, 2, 3});
        printSpline(spline, 3);
        spline = new CardinalUsingHermitSpline(new float[] {0, 1, 2, 3}, 0.5f);
        printSpline(spline, 3);
    }

    private static void printSpline(Spline spline, int times) {
        for (int i = 0; i <= 10*times; i++) {
            System.out.println(spline.interpolate(i / 10f));
        }
        System.out.println("--");
    }

    private static void testCurves() {
        Curve curve = new CardinalCurve(0, 1, 2, 3, 0.5f);
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
