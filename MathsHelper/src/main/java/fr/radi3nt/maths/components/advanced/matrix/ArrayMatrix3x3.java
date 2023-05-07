package fr.radi3nt.maths.components.advanced.matrix;

import fr.radi3nt.maths.components.advanced.matrix.angle.Angle;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;

import java.util.Arrays;

public class ArrayMatrix3x3 implements Matrix3x3 {

    private final float[][] m = new float[3][3];

    public ArrayMatrix3x3() {

    }

    public ArrayMatrix3x3(ArrayMatrix3x3 copy) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                set(i, j, copy.get(i, j));
            }
        }
    }

    public static ArrayMatrix3x3 newIdentity() {
        ArrayMatrix3x3 arrayMatrix4x4 = new ArrayMatrix3x3();
        arrayMatrix4x4.identity();
        return arrayMatrix4x4;
    }

    @Override
    public void identity() {
        zero();
        m[0][0] = 1;
        m[1][1] = 1;
        m[2][2] = 1;
    }

    @Override
    public void zero() {
        for (float[] floats : m) {
            Arrays.fill(floats, 0);
        }
    }

    @Override
    public void transpose() {
        float m10 = m[0][1];
        float m20 = m[0][2];
        float m01 = m[1][0];
        float m21 = m[1][2];
        float m02 = m[2][0];
        float m12 = m[2][1];

        m[1][0] = m10;
        m[2][0] = m20;
        m[0][1] = m01;
        m[2][1] = m21;
        m[0][2] = m02;
        m[1][2] = m12;
    }

    @Override
    public void negate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m[i][j] = -m[i][j];
            }
        }
    }

    @Override
    public void invert() {
        float det = get(0, 0) * (get(1, 1) * get(2, 2) - get(2, 1) * get(1, 2)) -
                get(0, 1) * (get(1, 0) * get(2, 2) - get(1, 2) * get(2, 0)) +
                get(0, 2) * (get(1, 0) * get(2, 1) - get(1, 1) * get(2, 0));

        if (det == 0)
            throw new IllegalStateException("Matrix isn't invertible");

        float invdet = 1.0f / det;

        ArrayMatrix3x3 invM = new ArrayMatrix3x3();

        set(0, 0, (get(1, 1) * get(2, 2) - get(2, 1) * get(1, 2)) * invdet);
        set(0, 1, (get(0, 2) * get(2, 1) - get(0, 1) * get(2, 2)) * invdet);
        set(0, 2, (get(0, 1) * get(1, 2) - get(0, 2) * get(1, 1)) * invdet);
        set(1, 0, (get(1, 2) * get(2, 0) - get(1, 0) * get(2, 2)) * invdet);
        set(1, 1, (get(0, 0) * get(2, 2) - get(0, 2) * get(2, 0)) * invdet);
        set(1, 2, (get(1, 0) * get(0, 2) - get(0, 0) * get(1, 2)) * invdet);
        set(2, 0, (get(1, 0) * get(2, 1) - get(2, 0) * get(1, 1)) * invdet);
        set(2, 1, (get(2, 0) * get(0, 1) - get(0, 0) * get(2, 1)) * invdet);
        set(2, 2, (get(0, 0) * get(1, 1) - get(1, 0) * get(0, 1)) * invdet);

        this.copy(invM);
    }

    @Override
    public float get(int x, int y) {
        return m[x][y];
    }

    @Override
    public void set(int x, int y, float value) {
        m[x][y] = value;
    }

    @Override
    public Matrix3x3 duplicate() {
        return new ArrayMatrix3x3(this);
    }

    @Override
    public Quaternion getRotation() {
        return getCopySignRotation();
    }

    private Quaternion getCopySignRotation() {
        float w = (float) (Math.sqrt(Math.max(0, 1 + m[0][0] + m[1][1] + m[2][2])) / 2);
        float x = (float) (Math.sqrt(Math.max(0, 1 + m[0][0] - m[1][1] - m[2][2])) / 2);
        float y = (float) (Math.sqrt(Math.max(0, 1 - m[0][0] + m[1][1] - m[2][2])) / 2);
        float z = (float) (Math.sqrt(Math.max(0, 1 - m[0][0] - m[1][1] + m[2][2])) / 2);
        x *= Math.signum(m[2][1] - m[1][2]);
        y *= Math.signum(m[0][2] - m[2][0]);
        z *= Math.signum(m[1][0] - m[0][1]);
        return new ComponentsQuaternion(x, y, z, w);
    }

    @Override
    public void add(Matrix3x3 other) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.m[i][j] += other.get(i, j);
            }
        }
    }

    @Override
    public void scale(Vector3f scale) {
        m[0][0] = scale.getX();
        m[1][1] = scale.getY();
        m[2][2] = scale.getZ();
    }

    @Override
    public void rotationX(Angle angle) {
        m[1][1] = angle.cos();
        m[1][2] = angle.sin();
        m[2][1] = -angle.sin();
        m[2][2] = angle.cos();
    }

    @Override
    public void rotationY(Angle angle) {
        m[0][0] = angle.cos();
        m[2][0] = angle.sin();
        m[0][2] = -angle.sin();
        m[2][2] = angle.cos();
    }

    @Override
    public void rotationZ(Angle angle) {
        m[0][0] = angle.cos();
        m[0][1] = angle.sin();
        m[1][0] = -angle.sin();
        m[1][1] = angle.cos();
    }

    @Override
    public void eulerRotation(Angle x, Angle y, Angle z) {
        float cosX = x.cos();
        float sinX = x.sin();
        float cosY = y.cos();
        float sinY = y.sin();
        float cosZ = z.cos();
        float sinZ = z.sin();

        float cosXsinY = cosX * sinY;
        float sinXsinY = sinX * sinY;

        m[0][0] = cosY * cosZ;
        m[0][1] = -cosY * sinZ;
        m[0][2] = sinY;
        m[1][0] = sinXsinY * cosZ + cosX * sinZ;
        m[1][1] = -sinXsinY * sinZ + cosX * cosZ;
        m[1][2] = -sinX * cosY;
        m[2][0] = -cosXsinY * cosZ + sinX * sinZ;
        m[2][1] = cosXsinY * sinZ + sinX * cosZ;
        m[2][2] = cosX * cosY;
    }

    @Override
    public void quaternionRotation(Quaternion quaternion) {
        float x = quaternion.getX();
        float y = quaternion.getY();
        float z = quaternion.getZ();

        float rotationValue = quaternion.getW();

        float xx = x * x;
        float xy = x * y;
        float xz = x * z;
        float xw = x * rotationValue;

        float yy = y * y;
        float yz = y * z;
        float yw = y * rotationValue;

        float zz = z * z;
        float zw = z * rotationValue;


        m[0][0] = 1 - 2 * (yy + zz);
        m[0][1] = 2 * (xy - zw);
        m[0][2] = 2 * (xz + yw);

        m[1][0] = 2 * (xy + zw);
        m[1][1] = 1 - 2 * (xx + zz);
        m[1][2] = 2 * (yz - xw);

        m[2][0] = 2 * (xz - yw);
        m[2][1] = 2 * (yz + xw);
        m[2][2] = 1 - 2 * (xx + yy);
    }

    @Override
    public void directionRotation(Vector3f direction, Vector3f up) {
        Vector3f x = up.duplicate().cross(direction);
        Vector3f y = direction.duplicate().cross(x);

        x.normalize();
        y.normalize();

        m[0][0] = x.getX();
        m[0][1] = y.getX();
        m[0][2] = direction.getX();
        m[0][3] = 0;
        m[1][0] = x.getY();
        m[1][1] = y.getY();
        m[1][2] = direction.getY();
        m[1][3] = 0;
        m[2][0] = x.getZ();
        m[2][1] = y.getZ();
        m[2][2] = direction.getZ();
    }

    @Override
    public void transform(Vector3f toTransform) {
        float x = m[0][0] * toTransform.getX() + m[1][0] * toTransform.getY() + m[2][0] * toTransform.getZ();
        float y = m[0][1] * toTransform.getX() + m[1][1] * toTransform.getY() + m[2][1] * toTransform.getZ();
        float z = m[0][2] * toTransform.getX() + m[1][2] * toTransform.getY() + m[2][2] * toTransform.getZ();
        toTransform.setX(x);
        toTransform.setY(y);
        toTransform.setZ(z);
    }

    @Override
    public void multiply(Matrix matrix) {
        Matrix result = new ArrayMatrix3x3();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                result.set(x, y, this.get(0, y) * matrix.get(x, 0) +
                        this.get(1, y) * matrix.get(x, 1) +
                        this.get(2, y) * matrix.get(x, 2));
            }
        }

        this.copy(result);
    }

    @Override
    public void copy(Matrix result) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                set(x, y, result.get(x, y));
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayMatrix3x3 that = (ArrayMatrix3x3) o;
        return Arrays.deepEquals(m, that.m);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(m);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ArrayMatrix3x3{" +
                "\n");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                stringBuilder.append(m[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
