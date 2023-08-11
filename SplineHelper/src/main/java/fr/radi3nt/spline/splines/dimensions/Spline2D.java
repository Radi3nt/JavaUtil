package fr.radi3nt.spline.splines.dimensions;

import fr.radi3nt.maths.components.vectors.Vector2f;

public interface Spline2D {

    Vector2f interpolate(float t);
    Vector2f velocity(float t);

}
