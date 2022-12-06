package fr.radi3nt.maths.components.matrices.implementation;

import fr.radi3nt.maths.components.matrices.Matrix;
import fr.radi3nt.maths.components.matrices.PerspectiveMatrix;
import fr.radi3nt.maths.components.matrices.ViewMatrix;
import fr.radi3nt.maths.components.vectors.Vector2f;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector4f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector4f;

import java.lang.reflect.Field;
import java.nio.FloatBuffer;

public class OpenGLMatrix implements Matrix, ViewMatrix, PerspectiveMatrix {

    public float m00;
    public float m01;
    public float m02;
    public float m03;
    public float m10;
    public float m11;
    public float m12;
    public float m13;
    public float m20;
    public float m21;
    public float m22;
    public float m23;
    public float m30;
    public float m31;
    public float m32;
    public float m33;

    public OpenGLMatrix() {
        this.identity();
    }

    public OpenGLMatrix(OpenGLMatrix src) {
        this.load(src);
    }

    public String toString() {
        String buf = String.valueOf(this.m00) + ' ' + this.m10 + ' ' + this.m20 + ' ' + this.m30 + '\n' +
                this.m01 + ' ' + this.m11 + ' ' + this.m21 + ' ' + this.m31 + '\n' +
                this.m02 + ' ' + this.m12 + ' ' + this.m22 + ' ' + this.m32 + '\n' +
                this.m03 + ' ' + this.m13 + ' ' + this.m23 + ' ' + this.m33 + '\n';
        return buf;
    }

    public Matrix identity() {
        return setIdentity(this);
    }

    public static OpenGLMatrix setIdentity(OpenGLMatrix m) {
        m.m00 = 1.0F;
        m.m01 = 0.0F;
        m.m02 = 0.0F;
        m.m03 = 0.0F;
        m.m10 = 0.0F;
        m.m11 = 1.0F;
        m.m12 = 0.0F;
        m.m13 = 0.0F;
        m.m20 = 0.0F;
        m.m21 = 0.0F;
        m.m22 = 1.0F;
        m.m23 = 0.0F;
        m.m30 = 0.0F;
        m.m31 = 0.0F;
        m.m32 = 0.0F;
        m.m33 = 1.0F;
        return m;
    }

    public Matrix zero() {
        return setZero(this);
    }

    @Override
    public Matrix translation(Vector3f vector3f) {
        identity();
        translate(vector3f);
        return this;
    }

    @Override
    public Matrix rotation(float value, Vector3f axis) {
        identity();
        rotate(value, axis);
        return this;
    }

    @Override
    public Matrix rotation(Vector3f rotation) {
        identity();
        rotate(rotation.getX(), new SimpleVector3f(1, 0, 0));
        rotate(rotation.getY(), new SimpleVector3f(0, 1, 0));
        rotate(rotation.getZ(), new SimpleVector3f(0, 0, 1));
        return this;
    }

    @Override
    public Matrix scaling(Vector3f scale) {
        identity();
        scale(scale);
        return this;
    }

    public static OpenGLMatrix setZero(OpenGLMatrix m) {
        m.m00 = 0.0F;
        m.m01 = 0.0F;
        m.m02 = 0.0F;
        m.m03 = 0.0F;
        m.m10 = 0.0F;
        m.m11 = 0.0F;
        m.m12 = 0.0F;
        m.m13 = 0.0F;
        m.m20 = 0.0F;
        m.m21 = 0.0F;
        m.m22 = 0.0F;
        m.m23 = 0.0F;
        m.m30 = 0.0F;
        m.m31 = 0.0F;
        m.m32 = 0.0F;
        m.m33 = 0.0F;
        return m;
    }

    public OpenGLMatrix load(OpenGLMatrix src) {
        return load(src, this);
    }

    public static OpenGLMatrix load(OpenGLMatrix src, OpenGLMatrix dest) {
        if (dest == null) {
            dest = new OpenGLMatrix();
        }

        dest.m00 = src.m00;
        dest.m01 = src.m01;
        dest.m02 = src.m02;
        dest.m03 = src.m03;
        dest.m10 = src.m10;
        dest.m11 = src.m11;
        dest.m12 = src.m12;
        dest.m13 = src.m13;
        dest.m20 = src.m20;
        dest.m21 = src.m21;
        dest.m22 = src.m22;
        dest.m23 = src.m23;
        dest.m30 = src.m30;
        dest.m31 = src.m31;
        dest.m32 = src.m32;
        dest.m33 = src.m33;
        return dest;
    }

    public Matrix load(FloatBuffer buf) {
        this.m00 = buf.get();
        this.m01 = buf.get();
        this.m02 = buf.get();
        this.m03 = buf.get();
        this.m10 = buf.get();
        this.m11 = buf.get();
        this.m12 = buf.get();
        this.m13 = buf.get();
        this.m20 = buf.get();
        this.m21 = buf.get();
        this.m22 = buf.get();
        this.m23 = buf.get();
        this.m30 = buf.get();
        this.m31 = buf.get();
        this.m32 = buf.get();
        this.m33 = buf.get();
        return this;
    }

    public Matrix loadTranspose(FloatBuffer buf) {
        this.m00 = buf.get();
        this.m10 = buf.get();
        this.m20 = buf.get();
        this.m30 = buf.get();
        this.m01 = buf.get();
        this.m11 = buf.get();
        this.m21 = buf.get();
        this.m31 = buf.get();
        this.m02 = buf.get();
        this.m12 = buf.get();
        this.m22 = buf.get();
        this.m32 = buf.get();
        this.m03 = buf.get();
        this.m13 = buf.get();
        this.m23 = buf.get();
        this.m33 = buf.get();
        return this;
    }

    public Matrix store(FloatBuffer buf) {
        buf.put(this.m00);
        buf.put(this.m01);
        buf.put(this.m02);
        buf.put(this.m03);
        buf.put(this.m10);
        buf.put(this.m11);
        buf.put(this.m12);
        buf.put(this.m13);
        buf.put(this.m20);
        buf.put(this.m21);
        buf.put(this.m22);
        buf.put(this.m23);
        buf.put(this.m30);
        buf.put(this.m31);
        buf.put(this.m32);
        buf.put(this.m33);
        return this;
    }

    @Override
    public Matrix duplicate() {
        return new OpenGLMatrix(this);
    }

    public Matrix storeTranspose(FloatBuffer buf) {
        buf.put(this.m00);
        buf.put(this.m10);
        buf.put(this.m20);
        buf.put(this.m30);
        buf.put(this.m01);
        buf.put(this.m11);
        buf.put(this.m21);
        buf.put(this.m31);
        buf.put(this.m02);
        buf.put(this.m12);
        buf.put(this.m22);
        buf.put(this.m32);
        buf.put(this.m03);
        buf.put(this.m13);
        buf.put(this.m23);
        buf.put(this.m33);
        return this;
    }

    public Matrix store3f(FloatBuffer buf) {
        buf.put(this.m00);
        buf.put(this.m01);
        buf.put(this.m02);
        buf.put(this.m10);
        buf.put(this.m11);
        buf.put(this.m12);
        buf.put(this.m20);
        buf.put(this.m21);
        buf.put(this.m22);
        return this;
    }

    public static OpenGLMatrix add(OpenGLMatrix left, OpenGLMatrix right, OpenGLMatrix dest) {
        if (dest == null) {
            dest = new OpenGLMatrix();
        }

        dest.m00 = left.m00 + right.m00;
        dest.m01 = left.m01 + right.m01;
        dest.m02 = left.m02 + right.m02;
        dest.m03 = left.m03 + right.m03;
        dest.m10 = left.m10 + right.m10;
        dest.m11 = left.m11 + right.m11;
        dest.m12 = left.m12 + right.m12;
        dest.m13 = left.m13 + right.m13;
        dest.m20 = left.m20 + right.m20;
        dest.m21 = left.m21 + right.m21;
        dest.m22 = left.m22 + right.m22;
        dest.m23 = left.m23 + right.m23;
        dest.m30 = left.m30 + right.m30;
        dest.m31 = left.m31 + right.m31;
        dest.m32 = left.m32 + right.m32;
        dest.m33 = left.m33 + right.m33;
        return dest;
    }

    public static OpenGLMatrix sub(OpenGLMatrix left, OpenGLMatrix right, OpenGLMatrix dest) {
        if (dest == null) {
            dest = new OpenGLMatrix();
        }

        dest.m00 = left.m00 - right.m00;
        dest.m01 = left.m01 - right.m01;
        dest.m02 = left.m02 - right.m02;
        dest.m03 = left.m03 - right.m03;
        dest.m10 = left.m10 - right.m10;
        dest.m11 = left.m11 - right.m11;
        dest.m12 = left.m12 - right.m12;
        dest.m13 = left.m13 - right.m13;
        dest.m20 = left.m20 - right.m20;
        dest.m21 = left.m21 - right.m21;
        dest.m22 = left.m22 - right.m22;
        dest.m23 = left.m23 - right.m23;
        dest.m30 = left.m30 - right.m30;
        dest.m31 = left.m31 - right.m31;
        dest.m32 = left.m32 - right.m32;
        dest.m33 = left.m33 - right.m33;
        return dest;
    }

    public static OpenGLMatrix mul(OpenGLMatrix left, Matrix right, OpenGLMatrix dest) {
        if (dest == null) {
            dest = new OpenGLMatrix();
        }

        /*

        float m00 = left.m00 * right.get(0, 0) + left.m10 * right.get(0, 1) + left.m20 * right.get(0, 2) + left.m30 * right.get(0, 3);
        float m01 = left.m01 * right.get(0, 0) + left.m11 * right.get(0, 1) + left.m21 * right.get(0, 2) + left.m31 * right.get(0, 3);
        float m02 = left.m02 * right.get(0, 0) + left.m12 * right.get(0, 1) + left.m22 * right.get(0, 2) + left.m32 * right.get(0, 3);
        float m03 = left.m03 * right.get(0, 0) + left.m13 * right.get(0, 1) + left.m23 * right.get(0, 2) + left.m33 * right.get(0, 3);
        float m10 = left.m00 * right.get(1, 0) + left.m10 * right.get(1, 1) + left.m20 * right.get(1, 2) + left.m30 * right.get(1, 3);
        float m11 = left.m01 * right.get(1, 0) + left.m11 * right.get(1, 1) + left.m21 * right.get(1, 2) + left.m31 * right.get(1, 3);
        float m12 = left.m02 * right.get(1, 0) + left.m12 * right.get(1, 1) + left.m22 * right.get(1, 2) + left.m32 * right.get(1, 3);
        float m13 = left.m03 * right.get(1, 0) + left.m13 * right.get(1, 1) + left.m23 * right.get(1, 2) + left.m33 * right.get(1, 3);
        float m20 = left.m00 * right.get(2, 0) + left.m10 * right.get(2, 1) + left.m20 * right.get(2, 2) + left.m30 * right.get(2, 3);
        float m21 = left.m01 * right.get(2, 0) + left.m11 * right.get(2, 1) + left.m21 * right.get(2, 2) + left.m31 * right.get(2, 3);
        float m22 = left.m02 * right.get(2, 0) + left.m12 * right.get(2, 1) + left.m22 * right.get(2, 2) + left.m32 * right.get(2, 3);
        float m23 = left.m03 * right.get(2, 0) + left.m13 * right.get(2, 1) + left.m23 * right.get(2, 2) + left.m33 * right.get(2, 3);
        float m30 = left.m00 * right.get(3, 0) + left.m10 * right.get(3, 1) + left.m20 * right.get(3, 2) + left.m30 * right.get(3, 3);
        float m31 = left.m01 * right.get(3, 0) + left.m11 * right.get(3, 1) + left.m21 * right.get(3, 2) + left.m31 * right.get(3, 3);
        float m32 = left.m02 * right.get(3, 0) + left.m12 * right.get(3, 1) + left.m22 * right.get(3, 2) + left.m32 * right.get(3, 3);
        float m33 = left.m03 * right.get(3, 0) + left.m13 * right.get(3, 1) + left.m23 * right.get(3, 2) + left.m33 * right.get(3, 3);
        dest.m00 = m00;
        dest.m01 = m01;
        dest.m02 = m02;
        dest.m03 = m03;
        dest.m10 = m10;
        dest.m11 = m11;
        dest.m12 = m12;
        dest.m13 = m13;
        dest.m20 = m20;
        dest.m21 = m21;
        dest.m22 = m22;
        dest.m23 = m23;
        dest.m30 = m30;
        dest.m31 = m31;
        dest.m32 = m32;
        dest.m33 = m33;



         */



        for (int i = 0; i < 4; i++) { //todo changed
            for (int j = 0; j < 4; j++) {
                dest.set(i, j, left.get(i,0) * right.get(0, j) +
                        left.get(i,1) * right.get(1, j) +
                        left.get(i,2) * right.get(2, j) +
                        left.get(i,3) * right.get(3, j));
            }
        }


        return dest;
    }

    public static Vector4f transform(OpenGLMatrix left, Vector4f right, Vector4f dest) {
        if (dest == null) {
            dest = new SimpleVector4f();
        }

        float x = left.m00 * right.getX() + left.m10 * right.getY() + left.m20 * right.getZ() + left.m30 * right.getW();
        float y = left.m01 * right.getX() + left.m11 * right.getY() + left.m21 * right.getZ() + left.m31 * right.getW();
        float z = left.m02 * right.getX() + left.m12 * right.getY() + left.m22 * right.getZ() + left.m32 * right.getW();
        float w = left.m03 * right.getX() + left.m13 * right.getY() + left.m23 * right.getZ() + left.m33 * right.getW();
        dest.setX(x);
        dest.setY(y);
        dest.setZ(z);
        dest.setW(w);
        return dest;
    }

    @Override
    public Vector4f transform(Vector4f vector4f) {
        return transform(this, vector4f, null);
    }

    @Override
    public Matrix mul(Matrix matrix) {
        return mul(this, matrix, null);
    }

    @Override
    public Matrix set(int x, int y, float value) {
        try {
            Field field = this.getClass().getField("m" + x + y);
            field.set(this, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public float get(int x, int y) {
        try {
            Field field = this.getClass().getField("m" + x + y);
            return (float) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Matrix multiply(Matrix matrix) {
        return mul(this, matrix, this);
    }

    public Matrix transpose() {
        return this.transpose(this);
    }

    public OpenGLMatrix translate(Vector2f vec) {
        return this.translate(vec, this);
    }

    public OpenGLMatrix translate(Vector3f vec) {
        return this.translate(vec, this);
    }

    public OpenGLMatrix scale(Vector3f vec) {
        return scale(vec, this, this);
    }

    public static OpenGLMatrix scale(Vector3f vec, OpenGLMatrix src, OpenGLMatrix dest) {
        if (dest == null) {
            dest = new OpenGLMatrix();
        }

        dest.m00 = src.m00 * vec.getX();
        dest.m01 = src.m01 * vec.getX();
        dest.m02 = src.m02 * vec.getX();
        dest.m03 = src.m03 * vec.getX();
        dest.m10 = src.m10 * vec.getY();
        dest.m11 = src.m11 * vec.getY();
        dest.m12 = src.m12 * vec.getY();
        dest.m13 = src.m13 * vec.getY();
        dest.m20 = src.m20 * vec.getZ();
        dest.m21 = src.m21 * vec.getZ();
        dest.m22 = src.m22 * vec.getZ();
        dest.m23 = src.m23 * vec.getZ();
        return dest;
    }

    public OpenGLMatrix rotate(float angle, Vector3f axis) {
        return this.rotate(angle, axis, this);
    }

    @Override
    public void rotationFromDirection(Vector3f direction, Vector3f up) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public OpenGLMatrix rotate(float angle, Vector3f axis, OpenGLMatrix dest) {
        return rotate(angle, axis, this, dest);
    }

    public static OpenGLMatrix rotate(float angle, Vector3f axis, OpenGLMatrix src, OpenGLMatrix dest) {
        if (dest == null) {
            dest = new OpenGLMatrix();
        }

        float c = (float) Math.cos(angle);
        float s = (float) Math.sin(angle);
        float oneminusc = 1.0F - c;
        float xy = axis.getX() * axis.getY();
        float yz = axis.getY() * axis.getZ();
        float xz = axis.getX() * axis.getZ();
        float xs = axis.getX() * s;
        float ys = axis.getY() * s;
        float zs = axis.getZ() * s;
        float f00 = axis.getX() * axis.getX() * oneminusc + c;
        float f01 = xy * oneminusc + zs;
        float f02 = xz * oneminusc - ys;
        float f10 = xy * oneminusc - zs;
        float f11 = axis.getY() * axis.getY() * oneminusc + c;
        float f12 = yz * oneminusc + xs;
        float f20 = xz * oneminusc + ys;
        float f21 = yz * oneminusc - xs;
        float f22 = axis.getZ() * axis.getZ() * oneminusc + c;
        float t00 = src.m00 * f00 + src.m10 * f01 + src.m20 * f02;
        float t01 = src.m01 * f00 + src.m11 * f01 + src.m21 * f02;
        float t02 = src.m02 * f00 + src.m12 * f01 + src.m22 * f02;
        float t03 = src.m03 * f00 + src.m13 * f01 + src.m23 * f02;
        float t10 = src.m00 * f10 + src.m10 * f11 + src.m20 * f12;
        float t11 = src.m01 * f10 + src.m11 * f11 + src.m21 * f12;
        float t12 = src.m02 * f10 + src.m12 * f11 + src.m22 * f12;
        float t13 = src.m03 * f10 + src.m13 * f11 + src.m23 * f12;
        dest.m20 = src.m00 * f20 + src.m10 * f21 + src.m20 * f22;
        dest.m21 = src.m01 * f20 + src.m11 * f21 + src.m21 * f22;
        dest.m22 = src.m02 * f20 + src.m12 * f21 + src.m22 * f22;
        dest.m23 = src.m03 * f20 + src.m13 * f21 + src.m23 * f22;
        dest.m00 = t00;
        dest.m01 = t01;
        dest.m02 = t02;
        dest.m03 = t03;
        dest.m10 = t10;
        dest.m11 = t11;
        dest.m12 = t12;
        dest.m13 = t13;
        return dest;
    }

    public OpenGLMatrix translate(Vector3f vec, OpenGLMatrix dest) {
        return translate(vec, this, dest);
    }

    public static OpenGLMatrix translate(Vector3f vec, OpenGLMatrix src, OpenGLMatrix dest) {
        if (dest == null) {
            dest = new OpenGLMatrix();
        }

        dest.m30 += src.m00 * vec.getX() + src.m10 * vec.getY() + src.m20 * vec.getZ();
        dest.m31 += src.m01 * vec.getX() + src.m11 * vec.getY() + src.m21 * vec.getZ();
        dest.m32 += src.m02 * vec.getX() + src.m12 * vec.getY() + src.m22 * vec.getZ();
        dest.m33 += src.m03 * vec.getX() + src.m13 * vec.getY() + src.m23 * vec.getZ();

        transpose(dest, dest);

        return dest;
    }

    public OpenGLMatrix translate(Vector2f vec, OpenGLMatrix dest) {
        return translate(vec, this, dest);
    }

    public static OpenGLMatrix translate(Vector2f vec, OpenGLMatrix src, OpenGLMatrix dest) {
        if (dest == null) {
            dest = new OpenGLMatrix();
        }

        dest.m30 += src.m00 * vec.getX() + src.m10 * vec.getY();
        dest.m31 += src.m01 * vec.getX() + src.m11 * vec.getY();
        dest.m32 += src.m02 * vec.getX() + src.m12 * vec.getY();
        dest.m33 += src.m03 * vec.getX() + src.m13 * vec.getY();
        return dest;
    }

    public OpenGLMatrix transpose(OpenGLMatrix dest) {
        return transpose(this, dest);
    }

    public static OpenGLMatrix transpose(OpenGLMatrix src, OpenGLMatrix dest) {
        if (dest == null) {
            dest = new OpenGLMatrix();
        }

        float m00 = src.m00;
        float m01 = src.m10;
        float m02 = src.m20;
        float m03 = src.m30;
        float m10 = src.m01;
        float m11 = src.m11;
        float m12 = src.m21;
        float m13 = src.m31;
        float m20 = src.m02;
        float m21 = src.m12;
        float m22 = src.m22;
        float m23 = src.m32;
        float m30 = src.m03;
        float m31 = src.m13;
        float m32 = src.m23;
        float m33 = src.m33;
        dest.m00 = m00;
        dest.m01 = m01;
        dest.m02 = m02;
        dest.m03 = m03;
        dest.m10 = m10;
        dest.m11 = m11;
        dest.m12 = m12;
        dest.m13 = m13;
        dest.m20 = m20;
        dest.m21 = m21;
        dest.m22 = m22;
        dest.m23 = m23;
        dest.m30 = m30;
        dest.m31 = m31;
        dest.m32 = m32;
        dest.m33 = m33;
        return dest;
    }

    public float determinant() {
        float f = this.m00 * (this.m11 * this.m22 * this.m33 + this.m12 * this.m23 * this.m31 + this.m13 * this.m21 * this.m32 - this.m13 * this.m22 * this.m31 - this.m11 * this.m23 * this.m32 - this.m12 * this.m21 * this.m33);
        f -= this.m01 * (this.m10 * this.m22 * this.m33 + this.m12 * this.m23 * this.m30 + this.m13 * this.m20 * this.m32 - this.m13 * this.m22 * this.m30 - this.m10 * this.m23 * this.m32 - this.m12 * this.m20 * this.m33);
        f += this.m02 * (this.m10 * this.m21 * this.m33 + this.m11 * this.m23 * this.m30 + this.m13 * this.m20 * this.m31 - this.m13 * this.m21 * this.m30 - this.m10 * this.m23 * this.m31 - this.m11 * this.m20 * this.m33);
        f -= this.m03 * (this.m10 * this.m21 * this.m32 + this.m11 * this.m22 * this.m30 + this.m12 * this.m20 * this.m31 - this.m12 * this.m21 * this.m30 - this.m10 * this.m22 * this.m31 - this.m11 * this.m20 * this.m32);
        return f;
    }

    private static float determinant3x3(float t00, float t01, float t02, float t10, float t11, float t12, float t20, float t21, float t22) {
        return t00 * (t11 * t22 - t12 * t21) + t01 * (t12 * t20 - t10 * t22) + t02 * (t10 * t21 - t11 * t20);
    }

    public Matrix invert() {
        return invert(this, this);
    }

    public static OpenGLMatrix invert(OpenGLMatrix src, OpenGLMatrix dest) {
        float determinant = src.determinant();
        if (determinant != 0.0F) {
            if (dest == null) {
                dest = new OpenGLMatrix();
            }

            float determinant_inv = 1.0F / determinant;
            float t00 = determinant3x3(src.m11, src.m12, src.m13, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
            float t01 = -determinant3x3(src.m10, src.m12, src.m13, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
            float t02 = determinant3x3(src.m10, src.m11, src.m13, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
            float t03 = -determinant3x3(src.m10, src.m11, src.m12, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
            float t10 = -determinant3x3(src.m01, src.m02, src.m03, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
            float t11 = determinant3x3(src.m00, src.m02, src.m03, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
            float t12 = -determinant3x3(src.m00, src.m01, src.m03, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
            float t13 = determinant3x3(src.m00, src.m01, src.m02, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
            float t20 = determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m31, src.m32, src.m33);
            float t21 = -determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m30, src.m32, src.m33);
            float t22 = determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m30, src.m31, src.m33);
            float t23 = -determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m30, src.m31, src.m32);
            float t30 = -determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m21, src.m22, src.m23);
            float t31 = determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m20, src.m22, src.m23);
            float t32 = -determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m20, src.m21, src.m23);
            float t33 = determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m20, src.m21, src.m22);
            dest.m00 = t00 * determinant_inv;
            dest.m11 = t11 * determinant_inv;
            dest.m22 = t22 * determinant_inv;
            dest.m33 = t33 * determinant_inv;
            dest.m01 = t10 * determinant_inv;
            dest.m10 = t01 * determinant_inv;
            dest.m20 = t02 * determinant_inv;
            dest.m02 = t20 * determinant_inv;
            dest.m12 = t21 * determinant_inv;
            dest.m21 = t12 * determinant_inv;
            dest.m03 = t30 * determinant_inv;
            dest.m30 = t03 * determinant_inv;
            dest.m13 = t31 * determinant_inv;
            dest.m31 = t13 * determinant_inv;
            dest.m32 = t23 * determinant_inv;
            dest.m23 = t32 * determinant_inv;
            return dest;
        } else {
            return null;
        }
    }

    public Matrix negate() {
        return this.negate(this);
    }

    public OpenGLMatrix negate(OpenGLMatrix dest) {
        return negate(this, dest);
    }

    public static OpenGLMatrix negate(OpenGLMatrix src, OpenGLMatrix dest) {
        if (dest == null) {
            dest = new OpenGLMatrix();
        }

        dest.m00 = -src.m00;
        dest.m01 = -src.m01;
        dest.m02 = -src.m02;
        dest.m03 = -src.m03;
        dest.m10 = -src.m10;
        dest.m11 = -src.m11;
        dest.m12 = -src.m12;
        dest.m13 = -src.m13;
        dest.m20 = -src.m20;
        dest.m21 = -src.m21;
        dest.m22 = -src.m22;
        dest.m23 = -src.m23;
        dest.m30 = -src.m30;
        dest.m31 = -src.m31;
        dest.m32 = -src.m32;
        dest.m33 = -src.m33;
        return dest;
    }

    @Override
    public Matrix perspective(float fov, float aspect, float near, float far) {
        identity();

        float y_scale = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))));
        float x_scale = y_scale;
        float frustum_length = far - near;

        this.m00 = x_scale;
        this.m11 = y_scale*aspect;
        this.m22 = -((far + near) / frustum_length);
        this.m23 = -1;
        this.m32 = -((2 * near * far) / frustum_length);
        this.m33 = 0;

        return this;
    }

    @Override
    public ViewMatrix view(Vector3f position, Vector3f rotation) {
        identity();

        OpenGLMatrix.rotate(-rotation.getY(), new SimpleVector3f(0, 1, 0), this, this);
        OpenGLMatrix.rotate(-rotation.getX(), new SimpleVector3f(1, 0, 0), this, this);
        OpenGLMatrix.rotate(-rotation.getZ(), new SimpleVector3f(0, 0, 1), this, this);

        this.multiply(new OpenGLMatrix().translate(position.duplicate().negate()));

        return this;
    }
}
