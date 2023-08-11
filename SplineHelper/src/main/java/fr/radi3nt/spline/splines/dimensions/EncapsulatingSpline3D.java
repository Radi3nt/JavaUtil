package fr.radi3nt.spline.splines.dimensions;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;
import fr.radi3nt.spline.splines.Spline;

public class EncapsulatingSpline3D implements Spline3D {

    private final Spline[] splines;

    public EncapsulatingSpline3D(Spline... splines) {
        this.splines = splines;
    }


    @Override
    public Vector3f interpolate(float t) {
        return new SimpleVector3f(interpolateIndex(0, t), interpolateIndex(1, t), interpolateIndex(2, t));
    }

    private float interpolateIndex(int index, float t) {
        return splines[index].interpolate(t);
    }

    @Override
    public Vector3f velocity(float t) {
        return new SimpleVector3f(velocityIndex(0, t), velocityIndex(1, t), velocityIndex(2, t));
    }

    private float velocityIndex(int index, float t) {
        return splines[index].velocity(t);
    }
}
