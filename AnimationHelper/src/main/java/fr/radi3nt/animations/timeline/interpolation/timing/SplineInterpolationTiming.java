package fr.radi3nt.animations.timeline.interpolation.timing;

import fr.radi3nt.spline.splines.Spline;

public class SplineInterpolationTiming implements InterpolationTiming {

    private final Spline spline;

    public SplineInterpolationTiming(Spline spline) {
        this.spline = spline;
    }

    @Override
    public float getTransformedTime(float time) {
        return spline.interpolate(time);
    }
}
