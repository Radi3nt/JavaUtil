package fr.radi3nt.animations.channels.single;

import fr.radi3nt.animations.timeline.interpolation.object.ObjectInterpolation;
import fr.radi3nt.animations.timeline.interpolation.timing.InterpolationTiming;

public class FloatInterpolation implements ObjectInterpolation<Float> {

    private final InterpolationTiming interpolationTiming;

    public FloatInterpolation(InterpolationTiming interpolationTiming) {
        this.interpolationTiming = interpolationTiming;
    }

    @Override
    public Float interpolate(Float start, Float end, float t) {
        float time = interpolationTiming.getTransformedTime(t);
        return start*(1-time)+end*time;
    }

}
