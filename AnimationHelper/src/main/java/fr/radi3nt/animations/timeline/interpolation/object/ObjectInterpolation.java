package fr.radi3nt.animations.timeline.interpolation.object;

public interface ObjectInterpolation<T> {

    T interpolate(T start, T end, float t);

}
