package fr.radi3nt.maths.components.vectors.implementations;

import fr.radi3nt.maths.components.vectors.Vector2l;

import java.util.Objects;

public class SimpleVector2l implements Vector2l {

    private long x;
    private long y;

    public SimpleVector2l(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public SimpleVector2l() {
    }

    @Override
    public long getX() {
        return x;
    }

    @Override
    public void setX(long x) {
        this.x = x;
    }

    @Override
    public long getY() {
        return y;
    }

    @Override
    public void setY(long y) {
        this.y = y;
    }

    @Override
    public Vector2l add(Vector2l vector2f) {
        return add(vector2f.getX(), vector2f.getY());
    }

    @Override
    public Vector2l add(long x, long y) {
        this.setX(this.getX()+x);
        this.setY(this.getY()+y);
        return this;
    }

    @Override
    public Vector2l sub(Vector2l vector2f) {
        return sub(vector2f.getX(), vector2f.getY());
    }

    @Override
    public Vector2l sub(long x, long y) {
        this.setX(this.getX()-x);
        this.setY(this.getY()-y);
        return this;
    }

    @Override
    public Vector2l mul(Vector2l vector2f) {
        return mul(vector2f.getX(), vector2f.getY());
    }

    @Override
    public Vector2l mul(long x, long y) {
        this.setX(this.getX()*x);
        this.setY(this.getY()*y);
        return this;
    }

    @Override
    public Vector2l mul(long mul) {
        return mul(mul, mul);
    }

    @Override
    public Vector2l div(Vector2l vector2f) {
        return div(vector2f.getX(), vector2f.getY());
    }

    @Override
    public Vector2l div(long x, long y) {
        this.setX(this.getX()/x);
        this.setY(this.getY()/y);
        return this;
    }

    @Override
    public Vector2l div(long mul) {
        return div(mul, mul);
    }

    @Override
    public Vector2l clone() {
        return new SimpleVector2l(getX(), getY());
    }

    @Override
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleVector2l that = (SimpleVector2l) o;
        return that.x == x && that.y == y;
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
