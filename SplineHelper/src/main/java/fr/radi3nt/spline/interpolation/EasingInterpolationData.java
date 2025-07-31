package fr.radi3nt.spline.interpolation;

import fr.radi3nt.maths.Easing;

public class EasingInterpolationData implements InterpolationData {

    private final Easing easing;

    public EasingInterpolationData(Easing easing) {
        this.easing = easing;
    }

    @Override
    public float interpolate(float t) {
        return easing.ease(t, 0, 1, 1);
    }
}
