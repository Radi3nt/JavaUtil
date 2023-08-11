package fr.radi3nt.animations.timeline.state;

import fr.radi3nt.animations.timeline.interpolation.object.ObjectInterpolation;

public class TypedAnimatedState<T> implements AnimatedState<T> {

    private final T start;
    private final T end;

    private final ObjectInterpolation<T> interpolation;
    private final float length;

    public TypedAnimatedState(T start, T end, ObjectInterpolation<T> interpolation, float length) {
        this.start = start;
        this.end = end;
        this.interpolation = interpolation;
        this.length = length;
    }

    @Override
    public T getStartState() {
        return start;
    }

    @Override
    public T getEndState() {
        return end;
    }

    @Override
    public T interpolate(float timeStartingAtBit) {
        return interpolation.interpolate(start, end, timeStartingAtBit/getTimeLength());
    }

    @Override
    public float getTimeLength() {
        return length;
    }
}
