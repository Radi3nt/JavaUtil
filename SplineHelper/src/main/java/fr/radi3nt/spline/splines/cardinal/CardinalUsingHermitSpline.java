package fr.radi3nt.spline.splines.cardinal;

import fr.radi3nt.spline.splines.Spline;
import fr.radi3nt.spline.splines.hermite.HermiteSpline;

public class CardinalUsingHermitSpline implements Spline {

    private final HermiteSpline hermiteSpline;

    public CardinalUsingHermitSpline(float[] pos, float scale) {
        float[] velocities = new float[pos.length];
        for (int i = 0; i < pos.length; i++) {
            int startIndex = i-1;
            int endIndex = i+1;
            float firstPos;
            if (startIndex<0)
                firstPos = pos[i]*2-pos[i+1];
            else
                firstPos = pos[i-1];

            float secondPos;
            if (endIndex>=pos.length)
                secondPos = pos[i]*2-pos[i-1];
            else
                secondPos = pos[i+1];

            float vel = secondPos-firstPos;
            velocities[i] = vel*scale;
        }

        hermiteSpline = new HermiteSpline(pos, velocities);
    }

    @Override
    public float interpolate(float t) {
        return hermiteSpline.interpolate(t);
    }

    @Override
    public float velocity(float t) {
        return hermiteSpline.velocity(t);
    }
}
