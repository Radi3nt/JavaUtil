package fr.radi3nt.maths.components.vectors.implementations;

import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.arbitrary.OperatingVectorNf;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector4f;

public class SimpleVector4f implements Vector4f {

    private float x = 0;
    private float y = 0;
    private float z = 0;
    private float w = 0;

    public SimpleVector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public SimpleVector4f(Vector3f vec, float w) {
        this.x = vec.getX();
        this.y = vec.getY();
        this.z = vec.getZ();
        this.w = w;
    }

    public SimpleVector4f() {
    }

    public SimpleVector4f(Quaternion quaternion) {
        this(quaternion.getX(), quaternion.getY(), quaternion.getZ(), quaternion.getW());
    }

    public static Vector4f fromAbs(Quaternion quaternion) {
        Vector4f vector = new SimpleVector4f(quaternion);
        if (quaternion.getW()<0)
            vector.mul(-1);
        return vector;
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
    public float getW() {
        return w;
    }

    @Override
    public void setW(float w) {
        this.w = w;
    }

    @Override
    public void normalize() {
        div(length());
    }

    @Override
    public void set(Vector3f vector3f, float w) {
        setX(vector3f.getX());
        setY(vector3f.getY());
        setZ(vector3f.getZ());
        setW(w);
    }

    @Override
    public void set(float x, float y, float z, float w) {
        setX(x);
        setY(y);
        setZ(z);
        setW(w);
    }

    @Override
    public void mul(Vector4f other) {
        this.x *= other.getX();
        this.y *= other.getY();
        this.z *= other.getZ();
        this.w *= other.getW();
    }

    @Override
    public float get(int row) {
        if (row==0)
            return x;
        if (row==1)
            return y;
        if (row==2)
            return z;
        if (row==3)
            return w;
        throw new IllegalArgumentException();
    }

    @Override
    public Vector4f duplicate() {
        return new SimpleVector4f(x, y, z, w);
    }

    public Vector4f div(float length) {
        this.setX(getX()/length);
        this.setY(getY()/length);
        this.setZ(getZ()/length);
        this.setW(getW()/length);
        return this;
    }

    @Override
    public float dot(Vector4f other) {
        return x * other.getX() + y * other.getY() + z * other.getZ() + w * other.getW();
    }

    @Override
    public OperatingVectorNf mul(float number) {
        this.setX(getX()*number);
        this.setY(getY()*number);
        this.setZ(getZ()*number);
        this.setW(getW()*number);
        return this;
    }

    @Override
    public OperatingVectorNf add(OperatingVectorNf other) {
        if (other.size()!=this.size())
            throw new IllegalArgumentException();
        this.x += other.get(0);
        this.y += other.get(1);
        this.z += other.get(2);
        this.w += other.get(3);
        return this;
    }

    @Override
    public OperatingVectorNf sub(OperatingVectorNf other) {
        if (other.size()!=this.size())
            throw new IllegalArgumentException();
        this.x -= other.get(0);
        this.y -= other.get(1);
        this.z -= other.get(2);
        this.w -= other.get(3);
        return this;
    }

    public Vector4f sub(Vector4f other) {
        if (other.size()!=this.size())
            throw new IllegalArgumentException();
        this.x -= other.getX();
        this.y -= other.getY();
        this.z -= other.getZ();
        this.w -= other.getW();
        return this;
    }

    @Override
    public int size() {
        return 4;
    }

    @Override
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    @Override
    public void copy(OperatingVectorNf other) {
        if (other.size()!=this.size()) {
            throw new IllegalArgumentException("Other vector doesn't have the same size");
        }
        x = other.get(0);
        y = other.get(1);
        z = other.get(2);
        w = other.get(3);
    }

    @Override
    public String toString() {
        return "SimpleVector4f{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
