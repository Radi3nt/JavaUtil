package fr.radi3nt.spline.splines.hermite;

import fr.radi3nt.spline.curve.Curve;
import fr.radi3nt.spline.curve.curves.HermiteCurve;
import fr.radi3nt.spline.splines.CollectionSpline;

import java.util.ArrayList;
import java.util.List;

public class HermiteSpline extends CollectionSpline {

    public HermiteSpline(float[] pos, float[] velocities) {
        this(new ArrayList<>(), pos, velocities);
    }

    public HermiteSpline(List<Curve> curves, float[] pos, float[] velocities) {
        super(curves);
        for (int i = 0; i < velocities.length-1; i++) {
            float currentPos = pos[i];
            float nextPos = pos[i+1];
            float currentVel = velocities[i];
            float nextVel = velocities[i+1];

            curves.add(new HermiteCurve(currentPos, nextPos, currentVel, nextVel));
        }
    }

    @Override
    public float interpolate(float t) {
        return super.interpolate(t);
    }
}
