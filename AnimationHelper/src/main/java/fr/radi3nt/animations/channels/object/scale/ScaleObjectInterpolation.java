package fr.radi3nt.animations.channels.object.scale;

import fr.radi3nt.animations.timeline.interpolation.object.ObjectInterpolation;
import fr.radi3nt.animations.timeline.interpolation.timing.InterpolationTiming;
import fr.radi3nt.maths.components.vectors.Vector3f;

public class ScaleObjectInterpolation implements ObjectInterpolation<Vector3f> {

    private final InterpolationTiming interpolationTiming;

    public ScaleObjectInterpolation(InterpolationTiming interpolationTiming) {
        this.interpolationTiming = interpolationTiming;
    }

    @Override
    public Vector3f interpolate(Vector3f start, Vector3f end, float t) {
        float time = interpolationTiming.getTransformedTime(t);
        return start.duplicate().mul(1-time).add(end.duplicate().mul(time));
    }
}
