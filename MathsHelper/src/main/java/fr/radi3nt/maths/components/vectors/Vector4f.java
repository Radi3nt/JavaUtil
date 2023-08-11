package fr.radi3nt.maths.components.vectors;

public interface Vector4f extends Vector {

    float getX();
    void setX(float x);
    float getY();
    void setY(float y);
    float getZ();
    void setZ(float z);
    float getW();
    void setW(float w);

    void div(float div);

    void normalize();

    void set(Vector3f vector3f, int w);
    void set(float x, float y, float z, int w);

}
