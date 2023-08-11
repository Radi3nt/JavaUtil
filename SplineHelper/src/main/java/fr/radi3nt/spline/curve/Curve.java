package fr.radi3nt.spline.curve;

public interface Curve {

    float interpolate(float t);
    float velocity(float t);

}
