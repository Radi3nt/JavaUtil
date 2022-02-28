package fr.radi3nt.maths.components.vectors;

public interface Vector2i extends Vector {

    int getX();
    void setX(int x);
    int getY();
    void setY(int y);

    Vector2i add(Vector2i Vector3f);
    Vector2i add(int x, int y);

    Vector2i sub(Vector2i Vector3f);
    Vector2i sub(int x, int y);

    Vector2i mul(Vector2i Vector3f);
    Vector2i mul(int x, int y);
    Vector2i mul(int mul);

    Vector2i div(Vector2i Vector3f);
    Vector2i div(int x, int y);
    Vector2i div(int mul);

    Vector2i clone();

    float dot(Vector2i Vector3f);
    Vector2i normalize();

    Vector2i negate();
}
