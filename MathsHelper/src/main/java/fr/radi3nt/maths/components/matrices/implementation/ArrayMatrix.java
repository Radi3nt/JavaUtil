package fr.radi3nt.maths.components.matrices.implementation;

import fr.radi3nt.maths.components.matrices.Matrix;
import fr.radi3nt.maths.components.matrices.PerspectiveMatrix;
import fr.radi3nt.maths.components.matrices.ViewMatrix;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector4f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector4f;

import java.nio.FloatBuffer;
import java.util.Arrays;

public class ArrayMatrix implements Matrix, PerspectiveMatrix, ViewMatrix {

    private float[][] m;

    ArrayMatrix(float[][] m) {
        this.m = m;
    }

    ArrayMatrix() {
        this(new float[4][4]);
        identity();
    }

    @Override
    public Matrix identity() {
        m[0][0] = 1; m[0][1] = 0; m[0][2] = 0; m[0][3] = 0;
        m[1][0] = 0; m[1][1] = 1; m[1][2] = 0; m[1][3] = 0;
        m[2][0] = 0; m[2][1] = 0; m[2][2] = 1; m[2][3] = 0;
        m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 1;

        return this;
    }

    @Override
    public Matrix zero() {
        m[0][0] = 0; m[0][1] = 0; m[0][2] = 0; m[0][3] = 0;
        m[1][0] = 0; m[1][1] = 0; m[1][2] = 0; m[1][3] = 0;
        m[2][0] = 0; m[2][1] = 0; m[2][2] = 0; m[2][3] = 0;
        m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 0;

        return this;
    }

    @Override
    public Matrix translation(Vector3f translation) {
        identity();
        m[0][0] = 1; m[0][1] = 0; m[0][2] = 0; m[0][3] = translation.getX();
        m[1][0] = 0; m[1][1] = 1; m[1][2] = 0; m[1][3] = translation.getY();
        m[2][0] = 0; m[2][1] = 0; m[2][2] = 1; m[2][3] = translation.getZ();
        m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 1;

        return this;
    }

    public static Matrix translate(Vector3f vec, Matrix src, Matrix dest) {
        if (dest == null) {
            dest = new ArrayMatrix();
        }

        dest.set(3, 0, dest.get(3, 0) + src.get(0, 0) * vec.getX() + src.get(1, 0) * vec.getY() + src.get(2, 0) * vec.getZ());
        dest.set(3, 1, dest.get(3, 1) + src.get(0, 1) * vec.getX() + src.get(1, 1) * vec.getY() + src.get(2, 1) * vec.getZ());
        dest.set(3, 2, dest.get(3, 2) + src.get(0, 2) * vec.getX() + src.get(1, 2) * vec.getY() + src.get(2, 2) * vec.getZ());
        dest.set(3, 3, dest.get(3, 3) + src.get(0, 3) * vec.getX() + src.get(1, 3) * vec.getY() + src.get(2, 3) * vec.getZ());
        return dest;
    }

    @Override
    public Matrix rotation(float value, Vector3f axis) {
        identity();
        return rotate(value, axis, this, this);
    }

    @Override
    public Matrix rotation(Vector3f rotation) {
        float x = (float) rotation.getX();
        float y = (float) rotation.getY();
        float z = (float) rotation.getZ();

        float sinX = (float)Math.sin(x);
        float sinY = (float)Math.sin(y);
        float sinZ = (float)Math.sin(z);

        float cosX = (float)Math.cos(x);
        float cosY = (float)Math.cos(y);
        float cosZ = (float)Math.cos(z);

        final float sinXsinY = sinX * sinY;
        final float cosXsinY = cosX * sinY;

        m[0][0] = cosY * cosZ;
        m[0][1] = cosY * sinZ;
        m[0][2] = -sinY;
        m[0][3] = 0f;

        m[1][0] = sinXsinY * cosZ - cosX * sinZ;
        m[1][1] = sinXsinY * sinZ + cosX * cosZ;
        m[1][2] = sinX * cosY;
        m[1][3] = 0f;

        m[2][0] = cosXsinY * cosZ + sinX * sinZ;
        m[2][1] = cosXsinY * sinZ - sinX * cosZ;
        m[2][2] = cosX * cosY;
        m[2][3] = 0f;

        m[3][0] = 0f;
        m[3][1] = 0f;
        m[3][2] = 0f;
        m[3][3] = 1f;

        return this;
    }

    public static Matrix rotate(float angle, Vector3f axis, Matrix src, Matrix dest) {
        if (dest == null) {
            dest = new ArrayMatrix();
        }

        float c = (float)Math.cos((double)angle);
        float s = (float)Math.sin((double)angle);
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
        float t00 = src.get(0, 0) * f00 + src.get(1, 0) * f01 + src.get(2, 0) * f02;
        float t01 = src.get(0, 1) * f00 + src.get(1, 1) * f01 + src.get(2, 1) * f02;
        float t02 = src.get(0, 2) * f00 + src.get(1, 2) * f01 + src.get(2, 2) * f02;
        float t03 = src.get(0, 3) * f00 + src.get(1, 3) * f01 + src.get(2, 3) * f02;
        float t10 = src.get(0, 0) * f10 + src.get(1, 0) * f11 + src.get(2, 0) * f12;
        float t11 = src.get(0, 1) * f10 + src.get(1, 1) * f11 + src.get(2, 1) * f12;
        float t12 = src.get(0, 2) * f10 + src.get(1, 2) * f11 + src.get(2, 2) * f12;
        float t13 = src.get(0, 3) * f10 + src.get(1, 3) * f11 + src.get(2, 3) * f12;
        dest.set(2, 0, src.get(0, 0) * f20 + src.get(1, 0) * f21 + src.get(2, 0) * f22);
        dest.set(2, 1, src.get(0, 1) * f20 + src.get(1, 1) * f21 + src.get(2, 1) * f22);
        dest.set(2, 2, src.get(0, 2) * f20 + src.get(1, 2) * f21 + src.get(2, 2) * f22);
        dest.set(2, 3, src.get(0, 3) * f20 + src.get(1, 3) * f21 + src.get(2, 3) * f22);
        dest.set(0, 0, t00);
        dest.set(0, 1, t01);
        dest.set(0, 2, t02);
        dest.set(0, 3, t03);
        dest.set(1, 0, t10);
        dest.set(1, 1, t11);
        dest.set(1, 2, t12);
        dest.set(1, 3, t13);
        return dest;
    }

    @Override
    public Matrix scaling(Vector3f scale) {
        identity();
        return scale(scale, this, this);
    }

    public static Matrix scale(Vector3f vec, Matrix src, Matrix dest) {
        if (dest == null) {
            dest = new ArrayMatrix();
        }

        dest.set(0, 0, src.get(0, 0)*vec.getX());
        dest.set(0, 1, src.get(0, 1)*vec.getX());
        dest.set(0, 2, src.get(0, 2)*vec.getX());
        dest.set(0, 3, src.get(0, 3)*vec.getX());

        dest.set(1, 0, src.get(1, 0)*vec.getY());
        dest.set(1, 1, src.get(1, 1)*vec.getY());
        dest.set(1, 2, src.get(1, 2)*vec.getY());
        dest.set(1, 3, src.get(1, 3)*vec.getY());

        dest.set(2, 0, src.get(2, 0)*vec.getZ());
        dest.set(2, 1, src.get(2, 1)*vec.getZ());
        dest.set(2, 2, src.get(2, 2)*vec.getZ());
        dest.set(2, 3, src.get(2, 3)*vec.getZ());

        return dest;
    }

    @Override
    public Matrix translate(Vector3f vector3f) {
        return translate(vector3f, this, this);
    }

    @Override
    public Matrix rotate(float value, Vector3f axis) {
        return rotate(value, axis, this, this);
    }

    @Override
    public Matrix scale(Vector3f scale) {
        return scale(scale, this, this);
    }

    @Override
    public Vector4f transform(Vector4f vector4f) {
        float x = this.m[0][0] * vector4f.getX() + this.m[1][0] * vector4f.getY() + this.m[2][0] * vector4f.getZ() + this.m[3][0] * vector4f.getW();
        float y = this.m[0][1] * vector4f.getX() + this.m[1][1] * vector4f.getY() + this.m[2][1] * vector4f.getZ() + this.m[3][1] * vector4f.getW();
        float z = this.m[0][2] * vector4f.getX() + this.m[1][2] * vector4f.getY() + this.m[2][2] * vector4f.getZ() + this.m[3][2] * vector4f.getW();
        float w = this.m[0][3] * vector4f.getX() + this.m[1][3] * vector4f.getY() + this.m[2][3] * vector4f.getZ() + this.m[3][3] * vector4f.getW();
        return new SimpleVector4f(x, y, z, w);
    }

    @Override
    public Matrix invert() {
        float s0 = get(0, 0) * get(1, 1) - get(1, 0) * get(0, 1);
        float s1 = get(0, 0) * get(1, 2) - get(1, 0) * get(0, 2);
        float s2 = get(0, 0) * get(1, 3) - get(1, 0) * get(0, 3);
        float s3 = get(0, 1) * get(1, 2) - get(1, 1) * get(0, 2);
        float s4 = get(0, 1) * get(1, 3) - get(1, 1) * get(0, 3);
        float s5 = get(0, 2) * get(1, 3) - get(1, 2) * get(0, 3);

        float c5 = get(2, 2) * get(3, 3) - get(3, 2) * get(2, 3);
        float c4 = get(2, 1) * get(3, 3) - get(3, 1) * get(2, 3);
        float c3 = get(2, 1) * get(3, 2) - get(3, 1) * get(2, 2);
        float c2 = get(2, 0) * get(3, 3) - get(3, 0) * get(2, 3);
        float c1 = get(2, 0) * get(3, 2) - get(3, 0) * get(2, 2);
        float c0 = get(2, 0) * get(3, 1) - get(3, 0) * get(2, 1);


        float div = (s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0);
        if (div == 0) System.err.println("not invertible");

        float invdet = 1.0f / div;

        ArrayMatrix invM = new ArrayMatrix();

        invM.set(0, 0, (get(1, 1) * c5 - get(1, 2) * c4 + get(1, 3) * c3) * invdet);
        invM.set(0, 1, (-get(0, 1) * c5 + get(0, 2) * c4 - get(0, 3) * c3) * invdet);
        invM.set(0, 2, (get(3, 1) * s5 - get(3, 2) * s4 + get(3, 3) * s3) * invdet);
        invM.set(0, 3, (-get(2, 1) * s5 + get(2, 2) * s4 - get(2, 3) * s3) * invdet);

        invM.set(1, 0, (-get(1, 0) * c5 + get(1, 2) * c2 - get(1, 3) * c1) * invdet);
        invM.set(1, 1, (get(0, 0) * c5 - get(0, 2) * c2 + get(0, 3) * c1) * invdet);
        invM.set(1, 2, (-get(3, 0) * s5 + get(3, 2) * s2 - get(3, 3) * s1) * invdet);
        invM.set(1, 3, (get(2, 0) * s5 - get(2, 2) * s2 + get(2, 3) * s1) * invdet);

        invM.set(2, 0, (get(1, 0) * c4 - get(1, 1) * c2 + get(1, 3) * c0) * invdet);
        invM.set(2, 1, (-get(0, 0) * c4 + get(0, 1) * c2 - get(0, 3) * c0) * invdet);
        invM.set(2, 2, (get(3, 0) * s4 - get(3, 1) * s2 + get(3, 3) * s0) * invdet);
        invM.set(2, 3, (-get(2, 0) * s4 + get(2, 1) * s2 - get(2, 3) * s0) * invdet);

        invM.set(3, 0, (-get(1, 0) * c3 + get(1, 1) * c1 - get(1, 2) * c0) * invdet);
        invM.set(3, 1, (get(0, 0) * c3 - get(0, 1) * c1 + get(0, 2) * c0) * invdet);
        invM.set(3, 2, (-get(3, 0) * s3 + get(3, 1) * s1 - get(3, 2) * s0) * invdet);
        invM.set(3, 3, (get(2, 0) * s3 - get(2, 1) * s1 + get(2, 2) * s0) * invdet);

        this.m = invM.getM();

        return this;
    }

    @Override
    public Matrix transpose() {
        ArrayMatrix result = new ArrayMatrix();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result.set(i, j, get(j, i));
            }
        }

        this.m = result.getM();

        return result;
    }

    @Override
    public Matrix multiply(Matrix matrix) {
        ArrayMatrix res = new ArrayMatrix();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res.set(i, j, this.get(i,0) * matrix.get(0, j) +
                        this.get(i,1) * matrix.get(1, j) +
                        this.get(i,2) * matrix.get(2, j) +
                        this.get(i,3) * matrix.get(3, j));
            }
        }

        m = res.getM();

        return this;
    }

    @Override
    public Matrix mul(Matrix matrix) {
        Matrix res = new ArrayMatrix();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res.set(i, j, this.get(i,0) * matrix.get(0, j) +
                        this.get(i,1) * matrix.get(1, j) +
                        this.get(i,2) * matrix.get(2, j) +
                        this.get(i,3) * matrix.get(3, j));
            }
        }

        return res;
    }

    @Override
    public Matrix perspective(float fov, float aspect, float near, float far) {
        identity();

        float y_scale = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))));
        float frustum_length = far - near;

        set(0, 0, y_scale);
        set(1, 1, y_scale*aspect);
        set(2, 2, -((far + near) / frustum_length));
        set(2, 3, -1);
        set(3, 2, -((2 * near * far) / frustum_length));
        set(3, 3, 0);

        return this;
    }

    @Override
    public ViewMatrix view(Vector3f position, Vector3f rotation) {
        identity();

        Matrix rotX = MatrixCreator.createMatrix().rotation(-rotation.getX(), new SimpleVector3f(1, 0, 0));
        Matrix rotY = MatrixCreator.createMatrix().rotation(-rotation.getY(), new SimpleVector3f(0, 1, 0));
        Matrix rotZ = MatrixCreator.createMatrix().rotation(-rotation.getZ(), new SimpleVector3f(0, 0, 1));

        Matrix translate = MatrixCreator.createMatrix().translation(new SimpleVector3f(-position.getX(), -position.getY(), -position.getZ()));


        this.multiply(rotX.mul(rotY).mul(rotZ).mul(translate));

        return this;
    }

    @Override
    public Matrix store(FloatBuffer buffer) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buffer.put(m[i][j]);
            }
        }
        return this;
    }

    @Override
    public Matrix set(int x, int y, float value) {
        m[x][y]=value;
        return this;
    }

    @Override
    public float get(int x, int y) {
        return m[x][y];
    }

    private float[][] getM() {
        return m;
    }

    @Override
    public Matrix duplicate() {
        float[][] m2 = new float[4][4];

        for (int x = 0; x < m.length; x++) {
            for (int y = 0; y < m[x].length; y++) {
                m2[x][y] = m[x][y];
            }
        }

        return new ArrayMatrix(m2);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ArrayMatrix{" +
                "\n");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                stringBuilder.append(m[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayMatrix that = (ArrayMatrix) o;
        return Arrays.deepEquals(m, that.m);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(m);
    }
}
