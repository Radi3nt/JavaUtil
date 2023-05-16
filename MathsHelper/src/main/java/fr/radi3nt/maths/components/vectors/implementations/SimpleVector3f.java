package fr.radi3nt.maths.components.vectors.implementations;

import fr.radi3nt.maths.components.Vector3D;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector3i;
import fr.radi3nt.maths.components.vectors.Vector4f;

import java.util.Objects;

public class SimpleVector3f implements Vector3f {

    private float x;
    private float y;
    private float z;

    public SimpleVector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SimpleVector3f(Vector3f vector3f) {
        this(vector3f.getX(), vector3f.getY(), vector3f.getZ());
    }

    public SimpleVector3f(Vector3i vector3f) {
        this(vector3f.getX(), vector3f.getY(), vector3f.getZ());
    }

    public SimpleVector3f() {
    }

    public SimpleVector3f(Vector3D multiply) {
        this((float) multiply.getX(), (float) multiply.getY(), (float) multiply.getZ());
    }

    public SimpleVector3f(Vector4f vector4f) {
        this(vector4f.getX(), vector4f.getY(), vector4f.getZ());
    }

    public SimpleVector3f(double v, double v1, double v2) {
        this((float) v, (float) v1, (float) v2);
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
    public float getZ() {
        return z;
    }

    @Override
    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public void set(float x, float y, float z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    @Override
    public Vector3f add(Vector3f vector3f) {
        return add(vector3f.getX(), vector3f.getY(), vector3f.getZ());
    }

    @Override
    public Vector3f add(float x, float y, float z) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
        this.setZ(this.getZ()+z);
        return this;
    }

    @Override
    public Vector3f sub(Vector3f vector2f) {
        return sub(vector2f.getX(), vector2f.getY(), vector2f.getZ());
    }

    @Override
    public Vector3f sub(float x, float y, float z) {
        this.setX(this.getX()-x);
        this.setY(this.getY()-y);
        this.setZ(this.getZ()-z);
        return this;
    }

    @Override
    public Vector3f mul(Vector3f vector2f) {
        return mul(vector2f.getX(), vector2f.getY(), vector2f.getZ());
    }

    @Override
    public Vector3f mul(float x, float y, float z) {
        this.setX(this.getX()*x);
        this.setY(this.getY()*y);
        this.setZ(this.getZ()*z);
        return this;
    }

    @Override
    public Vector3f mul(float mul) {
        return mul(mul, mul, mul);
    }

    @Override
    public Vector3f div(Vector3f vector2f) {
        return div(vector2f.getX(), vector2f.getY(), vector2f.getZ());
    }

    @Override
    public Vector3f div(float x, float y, float z) {
        this.setX(this.getX()/x);
        this.setY(this.getY()/y);
        this.setZ(this.getZ()/z);
        return this;
    }

    @Override
    public Vector3f div(float mul) {
        return div(mul, mul, mul);
    }

    @Override
    public Vector3f duplicate() {
        return new SimpleVector3f(x, y, z);
    }

    @Override
    public Vector3f cross(Vector3f vector3f) {
        float x = this.y * vector3f.getZ() - this.z * vector3f.getY();
        float y = this.z * vector3f.getX() - this.x * vector3f.getZ();
        float z = this.x * vector3f.getY() - this.y * vector3f.getX();

        setX(x);
        setY(y);
        setZ(z);

        return this;
    }

    @Override
    public float dot(Vector3f vector2f) {
        return x * vector2f.getX() + y * vector2f.getY() + z * vector2f.getZ();
    }

    @Override
    public Vector3f normalize() {
        return div(length());
    }

    @Override
    public Vector3f negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    @Override
    public float lengthSquared() {
        return x * x + y * y + z * z;
    }

    @Override
    public void copy(Vector3f vert1) {
        this.set(vert1.getX(), vert1.getY(), vert1.getZ());
    }

    @Override
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
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
        SimpleVector3f that = (SimpleVector3f) o;
        return Float.compare(that.x, x) == 0 && Float.compare(that.y, y) == 0 && Float.compare(that.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
