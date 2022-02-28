package fr.radi3nt.maths.components.vectors.implementations;

import fr.radi3nt.maths.components.vectors.Vector3b;

import java.util.Objects;

public class SimpleVector3b implements Vector3b {

    private byte x;
    private byte y;
    private byte z;

    public SimpleVector3b(byte x, byte y, byte z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SimpleVector3b(Vector3b vector3f) {
        this(vector3f.getX(), vector3f.getY(), vector3f.getZ());
    }

    public SimpleVector3b() {
    }

    @Override
    public byte getX() {
        return x;
    }

    @Override
    public void setX(byte x) {
        this.x = x;
    }

    @Override
    public byte getY() {
        return y;
    }

    @Override
    public void setY(byte y) {
        this.y = y;
    }

    @Override
    public byte getZ() {
        return z;
    }

    @Override
    public void setZ(byte z) {
        this.z = z;
    }

    @Override
    public Vector3b add(Vector3b vector3f) {
        return add(vector3f.getX(), vector3f.getY(), vector3f.getZ());
    }

    @Override
    public Vector3b add(byte x, byte y, byte z) {
        this.setX((byte) (this.getX()+x));
        this.setY((byte) (this.getY()+y));
        this.setZ((byte) (this.getZ()+z));
        return this;
    }

    @Override
    public Vector3b sub(Vector3b vector2f) {
        return sub(vector2f.getX(), vector2f.getY(), vector2f.getZ());
    }

    @Override
    public Vector3b sub(byte x, byte y, byte z) {
        this.setX((byte) (this.getX()-x));
        this.setY((byte) (this.getY()-y));
        this.setZ((byte) (this.getZ()-z));
        return this;
    }

    @Override
    public Vector3b mul(Vector3b vector2f) {
        return mul(vector2f.getX(), vector2f.getY(), vector2f.getZ());
    }

    @Override
    public Vector3b mul(byte x, byte y, byte z) {
        this.setX((byte) (this.getX()*x));
        this.setY((byte) (this.getY()*y));
        this.setZ((byte) (this.getZ()*z));
        return this;
    }

    @Override
    public Vector3b mul(byte mul) {
        return mul(mul, mul, mul);
    }

    @Override
    public Vector3b div(Vector3b vector2f) {
        return div(vector2f.getX(), vector2f.getY(), vector2f.getZ());
    }

    @Override
    public Vector3b div(byte x, byte y, byte z) {
        this.setX((byte) (this.getX()/x));
        this.setY((byte) (this.getY()/y));
        this.setZ((byte) (this.getZ()/z));
        return this;
    }

    @Override
    public Vector3b div(byte mul) {
        return div(mul, mul, mul);
    }

    @Override
    public Vector3b clone() {
        return new SimpleVector3b(x, y, z);
    }

    @Override
    public Vector3b cross(Vector3b vector3f) {
        byte x = (byte) (y * vector3f.getZ() - z * vector3f.getY());
        byte y = (byte) (z * vector3f.getX() - this.x * vector3f.getZ());
        byte z = (byte) (this.x * vector3f.getY() - this.y * vector3f.getX());

        setX(x);
        setY(y);
        setZ(z);

        return this;
    }

    @Override
    public float dot(Vector3b vector2f) {
        return x * vector2f.getX() + y * vector2f.getY() + z * vector2f.getZ();
    }

    @Override
    public Vector3b normalize() {
        return div((byte) length());
    }

    @Override
    public Vector3b negate() {
        this.x = (byte) -this.x;
        this.y = (byte) -this.y;
        this.z = (byte) -this.z;
        return this;
    }

    @Override
    public float length() {
        return (byte) Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public String toString() {
        return "SimpleVector3f{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleVector3b that = (SimpleVector3b) o;
        return that.x == x && that.y == y && that.z == z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
