package fr.radi3nt.animations.channels.object.rotation;

import fr.radi3nt.animations.timeline.interpolation.object.ObjectInterpolation;
import fr.radi3nt.animations.timeline.interpolation.timing.InterpolationTiming;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;

public class QuaternionObjectInterpolation implements ObjectInterpolation<Quaternion> {

    private final InterpolationTiming interpolationTiming;

    public QuaternionObjectInterpolation(InterpolationTiming interpolationTiming) {
        this.interpolationTiming = interpolationTiming;
    }

    @Override
    public Quaternion interpolate(Quaternion start, Quaternion end, float t) {
        float time = interpolationTiming.getTransformedTime(t);
        Quaternion result = start.duplicate();
        result.interpolate(end, time);
        return result;
    }
}
