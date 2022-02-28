package fr.radi3nt.maths.components.vectors;

public interface Vector2l extends Vector {

    long getX();
    void setX(long x);
    long getY();
    void setY(long y);

    Vector2l add(Vector2l vector2f);
    Vector2l add(long x, long y);

    Vector2l sub(Vector2l vector2f);
    Vector2l sub(long x, long y);

    Vector2l mul(Vector2l vector2f);
    Vector2l mul(long x, long y);
    Vector2l mul(long mul);

    Vector2l div(Vector2l vector2f);
    Vector2l div(long x, long y);
    Vector2l div(long mul);

    Vector2l clone();

}
