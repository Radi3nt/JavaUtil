package fr.radi3nt.spline.splines.dimensions;

import fr.radi3nt.maths.components.vectors.Vector3f;

public interface Spline3D {

    Vector3f interpolate(float t);
    Vector3f velocity(float t);

}
