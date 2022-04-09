package fr.radi3nt.maths.components.vectors.implementations;

import fr.radi3nt.maths.components.vectors.Vector2i;

public class SimpleVector2i implements Vector2i {

    private int x;
    private int y;

    public SimpleVector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SimpleVector2i(Vector2i vector3f) {
        this(vector3f.getX(), vector3f.getY());
    }

    public SimpleVector2i() {
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void set(int x, int y) {
        setX(x);
        setY(y);
    }

    @Override
    public Vector2i add(Vector2i vector3f) {
        return add(vector3f.getX(), vector3f.getY());
    }

    @Override
    public Vector2i add(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
        return this;
    }

    @Override
    public Vector2i sub(Vector2i vector2f) {
        return sub(vector2f.getX(), vector2f.getY());
    }

    @Override
    public Vector2i sub(int x, int y) {
        this.setX(this.getX()-x);
        this.setY(this.getY()-y);
        return this;
    }

    @Override
    public Vector2i mul(Vector2i vector2f) {
        return mul(vector2f.getX(), vector2f.getY());
    }

    @Override
    public Vector2i mul(int x, int y) {
        this.setX(this.getX()*x);
        this.setY(this.getY()*y);
        return this;
    }

    @Override
    public Vector2i mul(int mul) {
        return mul(mul, mul);
    }

    @Override
    public Vector2i div(Vector2i vector2f) {
        return div(vector2f.getX(), vector2f.getY());
    }

    @Override
    public Vector2i div(int x, int y) {
        this.setX(this.getX()/x);
        this.setY(this.getY()/y);
        return this;
    }

    @Override
    public Vector2i div(int mul) {
        return div(mul, mul);
    }

    @Override
    public Vector2i clone() {
        return new SimpleVector2i(x, y);
    }

    @Override
    public float dot(Vector2i vector2f) {
        return x * vector2f.getX() + y * vector2f.getY();
    }

    @Override
    public Vector2i normalize() {
        return div((int) length());
    }

    @Override
    public Vector2i negate() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    @Override
    public float length() {
        return (int) Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return "SimpleVector2i{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleVector2i that = (SimpleVector2i) o;
        return that.x == x && that.y == y;
    }

    @Override
    public int hashCode() {
        return x * 371 + y;
    }
}
