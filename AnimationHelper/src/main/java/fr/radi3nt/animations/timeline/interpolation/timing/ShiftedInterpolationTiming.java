package fr.radi3nt.animations.timeline.interpolation.timing;

public class ShiftedInterpolationTiming implements InterpolationTiming {

    private final InterpolationTiming encapsulated;
    private final float shiftT;

    public ShiftedInterpolationTiming(InterpolationTiming encapsulated, float shiftT) {
        this.encapsulated = encapsulated;
        this.shiftT = shiftT;
    }

    @Override
    public float getTransformedTime(float time) {
        return encapsulated.getTransformedTime(time+shiftT);
    }
}
