package fr.radi3nt.maths.dynamics.values;

public interface ValueDynamic<P> {

    void setTargetValue(float value);
    void setCurrentValue(float value);

    float getCurrentValue();
    void update(float delta);

    void setProperties(P properties);

}
