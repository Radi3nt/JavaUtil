package fr.radi3nt.maths.components.vectors;

import fr.radi3nt.maths.components.arbitrary.OperatingVectorNf;

public interface Vector4f extends Vector, OperatingVectorNf {

    float getX();
    void setX(float x);
    float getY();
    void setY(float y);
    float getZ();
    void setZ(float z);
    float getW();
    void setW(float w);

    Vector4f div(float div);
    float dot(Vector4f other);

    void normalize();

    void set(Vector3f vector3f, float w);
    void set(float x, float y, float z, float w);
    void mul(Vector4f other);

    Vector4f duplicate();

}
