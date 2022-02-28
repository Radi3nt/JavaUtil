package fr.radi3nt.maths.components.vectors;

public interface Vector3b extends Vector {

    byte getX();
    void setX(byte x);
    byte getY();
    void setY(byte y);
    byte getZ();
    void setZ(byte z);

    Vector3b add(Vector3b Vector3f);
    Vector3b add(byte x, byte y, byte z);

    Vector3b sub(Vector3b Vector3f);
    Vector3b sub(byte x, byte y, byte z);

    Vector3b mul(Vector3b Vector3f);
    Vector3b mul(byte x, byte y, byte z);
    Vector3b mul(byte mul);

    Vector3b div(Vector3b Vector3f);
    Vector3b div(byte x, byte y, byte z);
    Vector3b div(byte mul);

    Vector3b clone();

    Vector3b cross(Vector3b vector3f);
    float dot(Vector3b Vector3f);
    Vector3b normalize();

    Vector3b negate();
}
