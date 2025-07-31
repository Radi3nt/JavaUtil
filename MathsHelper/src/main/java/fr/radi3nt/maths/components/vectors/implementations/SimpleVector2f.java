package fr.radi3nt.maths.components.vectors.implementations;

import fr.radi3nt.maths.components.Vector2D;
import fr.radi3nt.maths.components.arbitrary.OperatingVectorNf;
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

    public SimpleVector2f(Vector2D vector) {
        this.x = (float) vector.getX();
        this.y = (float) vector.getY();
    }

    public SimpleVector2f(Vector2f vector) {
        this.copy(vector);
    }

    public static float distanceSquared(Vector2f first, Vector2f second) {
        float x = (first.getX()-second.getX());
        float y = (first.getY()-second.getY());
        return x*x + y*y;
    }

    public static float distance(float x, float y, float xo, float yo) {
        float xd = x-xo;
        float yd = y-yo;
        return (float) Math.sqrt(xd*xd+yd*yd);
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
    public OperatingVectorNf add(OperatingVectorNf other) {
        return add(other.get(0), other.get(1));
    }

    @Override
    public OperatingVectorNf sub(OperatingVectorNf other) {
        return sub(other.get(0), other.get(1));
    }

    @Override
    public int size() {
        return 2;
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
    public float get(int row) {
        if (row>1 || row<0)
            throw new UnsupportedOperationException();
        return row == 0 ? x : y;
    }

    @Override
    public Vector2f duplicate() {
        return clone();
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
    public Vector2f normalizeSafely() {
        float length = length();
        if (length==0)
            mul(0);
        else
            div(length);
        return this;
    }

    @Override
    public float lengthSquared() {
        return x * x + y * y;
    }

    @Override
    public void copy(Vector2f vector2f) {
        this.setX(vector2f.getX());
        this.setY(vector2f.getY());
    }

    @Override
    public float length() {
        return (float) Math.sqrt(lengthSquared());
    }

    @Override
    public void copy(OperatingVectorNf other) {
        this.copy(new SimpleVector2f(other.get(0), other.get(1)));
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
