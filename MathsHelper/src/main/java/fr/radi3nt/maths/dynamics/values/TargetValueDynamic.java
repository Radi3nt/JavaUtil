package fr.radi3nt.maths.dynamics.values;

public abstract class TargetValueDynamic<P> implements ValueDynamic<P> {

    protected P properties;

    protected float targetValue;
    protected float currentValue;

    public TargetValueDynamic(P properties) {
        this.properties = properties;
    }

    @Override
    public void setTargetValue(float value) {
        this.targetValue = value;
    }

    @Override
    public void setCurrentValue(float value) {
        this.currentValue = value;
    }

    @Override
    public float getCurrentValue() {
        return currentValue;
    }

    @Override
    public void setProperties(P properties) {
        this.properties = properties;
    }
}
