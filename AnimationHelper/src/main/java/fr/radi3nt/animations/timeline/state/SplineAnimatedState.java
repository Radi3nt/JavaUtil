package fr.radi3nt.animations.timeline.state;

import fr.radi3nt.animations.timeline.interpolation.timing.InterpolationTiming;

public class SplineAnimatedState implements AnimatedState<Float> {

    private final InterpolationTiming interpolation;
    private final float length;

    public SplineAnimatedState(InterpolationTiming interpolation, float length) {
        this.interpolation = interpolation;
        this.length = length;
    }

    @Override
    public Float getStartState() {
        return interpolation.getTransformedTime(0);
    }

    @Override
    public Float getEndState() {
        return interpolation.getTransformedTime(1f);
    }

    @Override
    public Float interpolate(float timeStartingAtBit) {
        return interpolation.getTransformedTime(timeStartingAtBit/length);
    }

    @Override
    public float getTimeLength() {
        return length;
    }
}
