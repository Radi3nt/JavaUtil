package fr.radi3nt.spline.splines.dimensions;

import fr.radi3nt.maths.components.vectors.Vector2f;

public interface Spline2D {

    float interpolateX(float t);
    float interpolateY(float t);

    Vector2f interpolate(float t);
    float velocityX(float t);
    float velocityY(float t);
    Vector2f velocity(float t);

    int getSegmentCount();

}
