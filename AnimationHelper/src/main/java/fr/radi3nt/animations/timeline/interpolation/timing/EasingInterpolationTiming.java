package fr.radi3nt.animations.timeline.interpolation.timing;

import fr.radi3nt.maths.Easing;

public class EasingInterpolationTiming implements InterpolationTiming {

    private final Easing easing;

    public EasingInterpolationTiming(Easing easing) {
        this.easing = easing;
    }

    @Override
    public float getTransformedTime(float time) {
        return easing.ease(time, 0, 1, 1);
    }
}
