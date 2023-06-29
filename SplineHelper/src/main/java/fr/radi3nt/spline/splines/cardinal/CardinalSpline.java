package fr.radi3nt.spline.splines.cardinal;

import fr.radi3nt.spline.curve.Curve;
import fr.radi3nt.spline.curve.curves.cardinal.CardinalCurve;
import fr.radi3nt.spline.splines.CollectionSpline;

import java.util.ArrayList;

public class CardinalSpline extends CollectionSpline {

    public CardinalSpline(float[] pos, float scale) {
        super(new ArrayList<>());

        for (int i = 0; i < pos.length-1; i++) {
            float positionA;

            if (i==0)
                positionA = pos[i]*2-pos[i+1];
            else
                positionA = pos[i-1];

            float positionB = pos[i];

            float positionC;
            if (i+1>=pos.length)
                positionC = positionB*2-positionA;
            else
                positionC = pos[i+1];

            float positionD;

            if (i==pos.length-2)
                positionD = positionC*2-positionB;
            else
                positionD = pos[i+2];

            Curve catmullRomCurve = createCurve(scale, positionA, positionB, positionC, positionD);
            curves.add(catmullRomCurve);
        }
    }

    protected Curve createCurve(float scale, float positionA, float positionB, float positionC, float positionD) {
        return new CardinalCurve(positionA, positionB, positionC, positionD, scale);
    }

}
