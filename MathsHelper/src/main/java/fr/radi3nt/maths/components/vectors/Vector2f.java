package fr.radi3nt.maths.components.vectors;

public interface Vector2f extends Vector {

    float getX();
    void setX(float x);
    float getY();
    void setY(float y);

    Vector2f add(Vector2f vector2f);
    Vector2f add(float x, float y);

    Vector2f sub(Vector2f vector2f);
    Vector2f sub(float x, float y);

    Vector2f mul(Vector2f vector2f);
    Vector2f mul(float x, float y);
    Vector2f mul(float mul);

    Vector2f div(Vector2f vector2f);
    Vector2f div(float x, float y);
    Vector2f div(float mul);

    Vector2f clone();

    float dot(Vector2f vector2f);
    Vector2f normalize();
    Vector2f normalizeSafely();

    float lengthSquared();

    void copy(Vector2f vector2f);
}
