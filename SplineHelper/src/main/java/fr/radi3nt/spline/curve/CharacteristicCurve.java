package fr.radi3nt.spline.curve;

public abstract class CharacteristicCurve implements Curve {

    @Override
    public float interpolate(float t) {
        float t2 = t*t;
        float t3 = t*t*t;
        return computeP(1, t, t2, t3);
    }

    @Override
    public float velocity(float t) {
        float t2 = t*t;
        return computeP(0, 1, 2*t, 3*t2);
    }

    protected abstract float computeP(float a, float t, float t2, float t3);
}
