package fr.radi3nt.animations.timeline.blending;

public interface ObjectBlending<T> {

    T blend(T first, T second, float secondWeight);
    T add(T first, T second, float weight);

    Class<T>[] supported();
}
