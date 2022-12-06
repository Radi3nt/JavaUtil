package fr.radi3nt.maths.components.advanced.matrix;

import fr.radi3nt.maths.components.advanced.matrix.angle.Angle;
import fr.radi3nt.maths.components.advanced.matrix.angle.JavaMathAngle;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector4f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

import java.util.Arrays;

public class ArrayMatrix4x4 implements Matrix4x4 {

    private final float[][] m = new float[4][4];

    public ArrayMatrix4x4() {
        zero();
    }

    @Override
    public void identity() {
        zero();
        m[0][0] = 1;
        m[1][1] = 1;
        m[2][2] = 1;
        m[3][3] = 1;
    }

    @Override
    public void zero() {
        for (float[] floats : m) {
            Arrays.fill(floats, 0);
        }
    }

    @Override
    public void transpose() {
        m[1][0] = m[0][1];
        m[2][0] = m[0][2];
        m[3][0] = m[0][3];
        m[0][1] = m[1][0];
        m[2][1] = m[1][2];
        m[3][1] = m[1][3];
        m[0][2] = m[2][0];
        m[1][2] = m[2][1];
        m[3][2] = m[2][3];
        m[0][3] = m[3][0];
        m[1][3] = m[3][1];
        m[2][3] = m[3][2];
    }

    @Override
    public void invert() {
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
        if (div == 0)
            throw new IllegalStateException("Matrix isn't invertible");

        float invdet = 1.0f / div;

        ArrayMatrix4x4 invM = new ArrayMatrix4x4();

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
    public Matrix duplicate() {
        ArrayMatrix4x4 arrayMatrix = new ArrayMatrix4x4();
        arrayMatrix.copy(this);
        return arrayMatrix;
    }

    @Override
    public Quaternion getRotation() {
        float t = 1 + m[0][0] + m[1][1] + m[2][2];

        if (t > 0.00000001) {
            float s = (float) (Math.sqrt(t) * 2f);
            float x = (m[1][2] - m[2][1]) / s;
            float y = (m[0][2] - m[2][0]) / s;
            float z = (m[1][0] - m[0][1]) / s;
            float w = 0.25f * s;

            return new ComponentsQuaternion(x, y, z, w);
        }

        if (m[0][0] > m[1][1] && m[0][0] > m[2][2]) {    // Column 0:
            float s = (float) (Math.sqrt(1.0 + m[0][0] - m[1][1] - m[2][2]) * 2f);
            float x = 0.25f * s;
            float y = (m[0][1] + m[1][0]) / s;
            float z = (m[2][0] + m[0][2]) / s;
            float w = (m[1][2] - m[2][1]) / s;

            return new ComponentsQuaternion(x, y, z, w);

        } else if (m[1][1] > m[2][2]) {            // Column 1:
            float s = (float) (Math.sqrt(1.0 + m[1][1] - m[0][0] - m[2][2]) * 2f);
            float x = (m[0][1] + m[1][0]) / s;
            float y = 0.25f * s;
            float z = (m[1][2] + m[2][1]) / s;
            float w = (m[2][0] - m[0][2]) / s;

            return new ComponentsQuaternion(x, y, z, w);

        } else {                        // Column 2:
            float s = (float) (Math.sqrt(1.0 + m[2][2] - m[0][0] - m[1][1]) * 2f);
            float x = (m[2][0] + m[0][2]) / s;
            float y = (m[1][2] + m[2][1]) / s;
            float z = 0.25f * s;
            float w = (m[0][1] - m[1][0]) / s;

            return new ComponentsQuaternion(x, y, z, w);
        }
    }

    @Override
    public Vector3f getTranslation() {
        return new SimpleVector3f(m[3][0], m[3][1], m[3][2]);
    }

    @Override
    public Vector3f getScale() {
        return new SimpleVector3f(m[0][0], m[1][1], m[2][2]);
    }

    @Override
    public void translation(Vector3f translation) {
        m[3][0] = translation.getX();
        m[3][1] = translation.getY();
        m[3][2] = translation.getZ();
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

        m[0][3] = m[1][3] = m[2][3] = m[3][0] = m[3][1] = m[3][2] = 0;
        m[3][3] = 1;
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

        m[0][3] = m[1][3] = m[2][3] = m[3][0] = m[3][1] = m[3][2] = 0;
        m[3][3] = 1;
    }

    @Override
    public void directionRotation(Vector3f direction, Vector3f up) {
        Vector3f xaxis = direction.duplicate().cross(up.duplicate());
        if (xaxis.getX() == 0 && xaxis.getY() == 0 && xaxis.getZ() == 0) {
            return;
        }
        xaxis.normalize();
        float phi = (float) Math.acos(direction.dot(up));


        float u = xaxis.getX();
        float w = xaxis.getY();
        float v = xaxis.getZ();

        /*
        float rcos = (float) Math.cos(phi);
        float rsin = (float) Math.sin(phi);
        m[0][0] =      rcos + u*u*(1-rcos);
        m[0][1] =  w * rsin + v*u*(1-rcos);
        m[0][2] = -v * rsin + w*u*(1-rcos);
        m[1][0] = -w * rsin + u*v*(1-rcos);
        m[1][1] =      rcos + v*v*(1-rcos);
        m[1][2] =  u * rsin + w*v*(1-rcos);
        m[2][0] =  v * rsin + u*w*(1-rcos);
        m[2][1] = -u * rsin + v*w*(1-rcos);
        m[2][2] =      rcos + w*w*(1-rcos);


         */
        Quaternion quaternion = ComponentsQuaternion.fromAxisAndAngle(xaxis, JavaMathAngle.fromRadiant(phi));
        quaternionRotation(quaternion);
    }

    @Override
    public void transform(Vector4f toTransform) {
        float x = m[0][0] * toTransform.getX() + m[1][0] * toTransform.getY() + m[2][0] * toTransform.getZ() + m[3][0] * toTransform.getW();
        float y = m[0][1] * toTransform.getX() + m[1][1] * toTransform.getY() + m[2][1] * toTransform.getZ() + m[3][1] * toTransform.getW();
        float z = m[0][2] * toTransform.getX() + m[1][2] * toTransform.getY() + m[2][2] * toTransform.getZ() + m[3][2] * toTransform.getW();
        float w = m[0][3] * toTransform.getX() + m[1][3] * toTransform.getY() + m[2][3] * toTransform.getZ() + m[3][3] * toTransform.getW();
        toTransform.setX(x);
        toTransform.setY(y);
        toTransform.setZ(z);
        toTransform.setW(w);
    }

    @Override
    public void multiply(Matrix matrix) {
        Matrix result = new ArrayMatrix4x4();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                result.set(x, y, this.get(0, y) * matrix.get(x, 0) +
                        this.get(1, y) * matrix.get(x, 1) +
                        this.get(2, y) * matrix.get(x, 2) +
                        this.get(3, y) * matrix.get(x, 3));
            }
        }

        this.copy(result);
    }

    @Override
    public void copy(Matrix result) {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                set(x, y, result.get(x, y));
            }
        }
    }

    public float[][] getM() {
        return m;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayMatrix4x4 that = (ArrayMatrix4x4) o;
        return Arrays.deepEquals(m, that.m);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(m);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ArrayMatrix4x4{" +
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
}
