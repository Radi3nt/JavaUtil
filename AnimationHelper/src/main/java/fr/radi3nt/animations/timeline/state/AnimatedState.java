package fr.radi3nt.animations.timeline.state;

public interface AnimatedState<T> {

    T getStartState();
    T getEndState();

    T interpolate(float timeStartingAtBit);
    float getTimeLength();

}
