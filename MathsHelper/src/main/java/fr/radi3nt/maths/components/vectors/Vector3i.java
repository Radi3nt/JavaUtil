package fr.radi3nt.maths.components.vectors;

public interface Vector3i extends Vector {

    int getX();
    void setX(int x);
    int getY();
    void setY(int y);
    int getZ();
    void setZ(int z);

    Vector3i add(Vector3i Vector3f);
    Vector3i add(int x, int y, int z);

    Vector3i sub(Vector3i Vector3f);
    Vector3i sub(int x, int y, int z);

    Vector3i mul(Vector3i Vector3f);
    Vector3i mul(int x, int y, int z);
    Vector3i mul(int mul);

    Vector3i div(Vector3i Vector3f);
    Vector3i div(int x, int y, int z);
    Vector3i div(int mul);

    Vector3i floorDiv(int mul);

    Vector3i clone();

    Vector3i cross(Vector3i vector3f);
    float dot(Vector3i Vector3f);
    Vector3i normalize();

    Vector3i negate();
}
