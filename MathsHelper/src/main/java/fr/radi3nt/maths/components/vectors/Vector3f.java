package fr.radi3nt.maths.components.vectors;

import fr.radi3nt.maths.components.arbitrary.OperatingVectorNf;

public interface Vector3f extends Vector, OperatingVectorNf {

    float getX();

    void setX(float x);

    float getY();

    void setY(float y);

    float getZ();

    void setZ(float z);

    void set(float x, float y, float z);

    Vector3f add(Vector3f Vector3f);

    Vector3f add(float x, float y, float z);

    Vector3f sub(Vector3f Vector3f);

    Vector3f sub(float x, float y, float z);

    Vector3f mul(Vector3f Vector3f);

    Vector3f mul(float x, float y, float z);

    Vector3f mul(float mul);

    Vector3f div(Vector3f Vector3f);

    Vector3f div(float x, float y, float z);

    Vector3f div(float mul);

    Vector3f duplicate();

    Vector3f cross(Vector3f vector3f);

    float dot(Vector3f Vector3f);

    Vector3f normalize();

    Vector3f negate();

    float lengthSquared();

    void copy(Vector3f vert1);

    void set(Vector3f set);

    void add(int row, float value);

    float get(int row);

    Vector3f normalizeSafely();
}
