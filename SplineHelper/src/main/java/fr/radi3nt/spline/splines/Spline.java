package fr.radi3nt.spline.splines;

public interface Spline {

    float interpolate(float t);
    float velocity(float t);

}
