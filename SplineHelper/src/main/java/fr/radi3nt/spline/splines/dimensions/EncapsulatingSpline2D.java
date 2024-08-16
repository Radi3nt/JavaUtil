package fr.radi3nt.spline.splines.dimensions;

import fr.radi3nt.maths.components.vectors.Vector2f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector2f;
import fr.radi3nt.spline.splines.Spline;

import java.util.Arrays;

public class EncapsulatingSpline2D implements Spline2D {

    private final Spline[] splines;

    public EncapsulatingSpline2D(Spline... splines) {
        this.splines = splines;
    }


    @Override
    public float interpolateX(float t) {
        return splines[0].interpolate(t);
    }

    @Override
    public float interpolateY(float t) {
        return splines[1].interpolate(t);
    }

    @Override
    public Vector2f interpolate(float t) {
        return new SimpleVector2f(interpolateIndex(0, t), interpolateIndex(1, t));
    }

    @Override
    public float velocityX(float t) {
        return splines[0].velocity(t);
    }

    @Override
    public float velocityY(float t) {
        return splines[1].velocity(t);
    }

    private float interpolateIndex(int index, float t) {
        return splines[index].interpolate(t);
    }

    @Override
    public Vector2f velocity(float t) {
        return new SimpleVector2f(velocityIndex(0, t), velocityIndex(1, t));
    }

    @Override
    public int getSegmentCount() {
        return splines[0].getSegmentCount();
    }

    private float velocityIndex(int index, float t) {
        return splines[index].velocity(t);
    }

    public Spline[] getSplines() {
        return splines;
    }

    @Override
    public String toString() {
        return "EncapsulatingSpline2D{" +
                "splines=" + Arrays.toString(splines) +
                '}';
    }
}
