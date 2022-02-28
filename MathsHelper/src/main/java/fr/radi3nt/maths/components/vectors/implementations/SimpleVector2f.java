package fr.radi3nt.maths.components.vectors.implementations;

import fr.radi3nt.maths.components.vectors.Vector2f;

import java.util.Objects;

public class SimpleVector2f implements Vector2f {

    private float x;
    private float y;

    public SimpleVector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public SimpleVector2f() {
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public Vector2f add(Vector2f vector2f) {
        return add(vector2f.getX(), vector2f.getY());
    }

    @Override
    public Vector2f add(float x, float y) {
        this.setX(this.getX()+x);
        this.setY(this.getY()+y);
        return this;
    }

    @Override
    public Vector2f sub(Vector2f vector2f) {
        return sub(vector2f.getX(), vector2f.getY());
    }

    @Override
    public Vector2f sub(float x, float y) {
        this.setX(this.getX()-x);
        this.setY(this.getY()-y);
        return this;
    }

    @Override
    public Vector2f mul(Vector2f vector2f) {
        return mul(vector2f.getX(), vector2f.getY());
    }

    @Override
    public Vector2f mul(float x, float y) {
        this.setX(this.getX()*x);
        this.setY(this.getY()*y);
        return this;
    }

    @Override
    public Vector2f mul(float mul) {
        return mul(mul, mul);
    }

    @Override
    public Vector2f div(Vector2f vector2f) {
        return div(vector2f.getX(), vector2f.getY());
    }

    @Override
    public Vector2f div(float x, float y) {
        this.setX(this.getX()/x);
        this.setY(this.getY()/y);
        return this;
    }

    @Override
    public Vector2f div(float mul) {
        return div(mul, mul);
    }

    @Override
    public Vector2f clone() {
        return new SimpleVector2f(getX(), getY());
    }

    @Override
    public float dot(Vector2f vector2f) {
        return x * vector2f.getX() + y * vector2f.getY();
    }

    @Override
    public Vector2f normalize() {
        return div(length());
    }

    @Override
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleVector2f that = (SimpleVector2f) o;
        return Float.compare(that.x, x) == 0 && Float.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "SimpleVector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
