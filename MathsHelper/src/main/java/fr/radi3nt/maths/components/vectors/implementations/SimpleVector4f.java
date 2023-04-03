package fr.radi3nt.maths.components.vectors.implementations;

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
    public void set(Vector3f vector3f, int w) {
        setX(vector3f.getX());
        setY(vector3f.getY());
        setZ(vector3f.getZ());
        setW(w);
    }

    public void div(float length) {
        this.setX(getX()/length);
        this.setY(getY()/length);
        this.setZ(getZ()/length);
        this.setW(getW()/length);
    }

    @Override
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
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
