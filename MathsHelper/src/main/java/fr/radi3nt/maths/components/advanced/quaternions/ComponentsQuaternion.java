package fr.radi3nt.maths.components.advanced.quaternions;

import fr.radi3nt.maths.components.advanced.matrix.angle.Angle;
import fr.radi3nt.maths.components.vectors.Vector3f;

import java.util.Objects;

public class ComponentsQuaternion implements Quaternion {

    private float x;
    private float y;
    private float z;
    private float w;

    public ComponentsQuaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public static Quaternion fromVectorToAnother(Vector3f v1, Vector3f v2) {
        Vector3f vector = v1.duplicate().cross(v2);
        float w = (float) (Math.sqrt((v1.lengthSquared()) * (v2.lengthSquared())) + v1.dot(v2));
        ComponentsQuaternion componentsQuaternion = new ComponentsQuaternion(vector.getX(), vector.getY(), vector.getZ(), w);
        componentsQuaternion.normalise();
        return componentsQuaternion;
    }

    public static Quaternion fromAxisAndAngle(Vector3f axis, Angle angle) {
        Vector3f actualAxis = axis.duplicate().normalize();

        float sin_a = (float) Math.sin(angle.getRadiant() / 2);
        float cos_a = (float) Math.cos(angle.getRadiant() / 2);

        float x = actualAxis.getX() * sin_a;
        float y = actualAxis.getY() * sin_a;
        float z = actualAxis.getZ() * sin_a;
        float w = cos_a;

        return new ComponentsQuaternion(x, y, z, w);
    }

    public static Quaternion fromEulerAngles(Angle angleX, Angle angleY, Angle angleZ) {
        float sinPitch = (float) Math.sin(angleX.getRadiant() * 0.5F);
        float cosPitch = (float) Math.cos(angleX.getRadiant() * 0.5F);
        float sinYaw = (float) Math.sin(angleY.getRadiant() * 0.5F);
        float cosYaw = (float) Math.cos(angleY.getRadiant() * 0.5F);
        float sinRoll = (float) Math.sin(angleZ.getRadiant() * 0.5F);
        float cosRoll = (float) Math.cos(angleZ.getRadiant() * 0.5F);
        float cosPitchCosYaw = (cosPitch * cosYaw);
        float sinPitchSinYaw = (sinPitch * sinYaw);

        float x = sinRoll * cosPitchCosYaw - cosRoll * sinPitchSinYaw;
        float y = cosRoll * sinPitch * cosYaw + sinRoll * cosPitch * sinYaw;
        float z = cosRoll * cosPitch * sinYaw - sinRoll * sinPitch * cosYaw;
        float w = cosRoll * cosPitchCosYaw + sinRoll * sinPitchSinYaw;

        return new ComponentsQuaternion(x, y, z, w);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getZ() {
        return z;
    }

    @Override
    public float getW() {
        return w;
    }

    @Override
    public void normalise() {
        float magnitude = getMagnitude();
        this.x /= magnitude;
        this.y /= magnitude;
        this.z /= magnitude;
        this.w /= magnitude;
    }

    @Override
    public void multiply(Quaternion quaternion) {
        float otherW = quaternion.getW();
        float otherX = quaternion.getX();
        float otherY = quaternion.getY();
        float otherZ = quaternion.getZ();

        float w = this.w * otherW - this.x * otherX - this.y * otherY - this.z * otherZ;
        float x = this.w * otherX + this.x * otherW + this.y * otherZ - this.z * otherY;
        float y = this.w * otherY + this.y * otherW + this.z * otherX - this.x * otherZ;
        float z = this.w * otherZ + this.z * otherW + this.x * otherY - this.y * otherX;

        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public float getMagnitude() {
        return (float) Math.sqrt(w * w + x * x + y * y + z * z);
    }

    @Override
    public Quaternion getConjugate() {
        return new ComponentsQuaternion(-x, -y, -z, w);
    }

    @Override
    public Quaternion duplicate() {
        return new ComponentsQuaternion(x, y, z, w);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentsQuaternion that = (ComponentsQuaternion) o;
        return Float.compare(that.x, x) == 0 && Float.compare(that.y, y) == 0 && Float.compare(that.z, z) == 0 && Float.compare(that.w, w) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public String toString() {
        return "ComponentsQuaternion{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
