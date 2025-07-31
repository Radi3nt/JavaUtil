package fr.radi3nt.maths.components.advanced.quaternions;

import fr.radi3nt.maths.Maths;
import fr.radi3nt.maths.components.advanced.matrix.angle.Angle;
import fr.radi3nt.maths.components.advanced.matrix.angle.JavaMathAngle;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector4f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

import java.util.Objects;

import static fr.radi3nt.maths.Maths.clamp;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class ComponentsQuaternion implements Quaternion {

    private static final Vector3f UP = new SimpleVector3f(0, 1, 0);
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

    public ComponentsQuaternion(Vector3f vector, float w) {
        this(vector.getX(), vector.getY(), vector.getZ(), w);
    }

    public ComponentsQuaternion(Vector4f vector) {
        this(vector.getX(), vector.getY(), vector.getZ(), vector.getW());
    }

    @Deprecated
    public static Quaternion fromVectorToAnother(Vector3f v1, Vector3f v2) {
        Vector3f vector = v1.duplicate().cross(v2);
        float w = (float) (sqrt((v1.lengthSquared()) * (v2.lengthSquared())) + v1.dot(v2));
        Quaternion componentsQuaternion = new ComponentsQuaternion(vector.getX(), vector.getY(), vector.getZ(), w);
        componentsQuaternion.normalise();
        return componentsQuaternion;
    }

    public static Quaternion fromTwoVectors(Vector3f u, Vector3f v) {
        float norm_u_norm_v = (float) sqrt(u.lengthSquared() * v.lengthSquared());
        float real_part = norm_u_norm_v + u.dot(v);
        Vector3f w;

        if (real_part < 1.e-6f * norm_u_norm_v) {
            /* If u and v are exactly opposite, rotate 180 degrees
             * around an arbitrary orthogonal axis. Axis normalisation
             * can happen later, when we normalise the quaternion. */
            real_part = 0.0f;
            w = abs(u.getX()) > abs(u.getZ()) ? new SimpleVector3f(-u.getY(), u.getX(), 0.f)
                    : new SimpleVector3f(0.f, -u.getZ(), u.getY());
        } else {
            /* Otherwise, build quaternion the standard way. */
            w = u.duplicate().cross(v);
        }

        Quaternion quaternion = new ComponentsQuaternion(w.getX(), w.getY(), w.getZ(), real_part);
        quaternion.normaliseSafely();
        return quaternion;
    }

    public static Quaternion fromAxisAndAngle(Vector3f axis, Angle angle) {
        Vector3f actualAxis = axis.duplicate().normalize();

        float sin_a = (float) Math.sin(angle.getRadiant() / 2);
        float cos_a = (float) Math.cos(angle.getRadiant() / 2);

        float x = actualAxis.getX() * sin_a;
        float y = actualAxis.getY() * sin_a;
        float z = actualAxis.getZ() * sin_a;

        return new ComponentsQuaternion(x, y, z, clamp(cos_a, -1, 1));
    }

    public static Quaternion fromEulerAngles(Angle angleX, Angle angleY, Angle angleZ) {
        Quaternion rotationX = ComponentsQuaternion.fromAxisAndAngle(new SimpleVector3f(1, 0, 0), angleX);
        Quaternion rotationY = ComponentsQuaternion.fromAxisAndAngle(new SimpleVector3f(0, 1, 0), angleY);
        Quaternion rotationZ = ComponentsQuaternion.fromAxisAndAngle(new SimpleVector3f(0, 0, 1), angleZ);

        rotationZ.multiply(rotationY);
        rotationZ.multiply(rotationX);

        return rotationZ;
    }

    public static Quaternion zero() {
        return new ComponentsQuaternion(0, 0, 0, 1);
    }

    public static Quaternion fromVec4(Vector4f response) {
        return new ComponentsQuaternion(response.getX(), response.getY(), response.getZ(), response.getW());
    }

    public static Quaternion fromUpVector(Vector3f other) {
        return fromTwoVectors(UP, other);
    }

    @Override
    public Quaternion getRotationComponentAboutAxis(
            Vector3f direction) {
        Vector3f rotationAxis = new SimpleVector3f(this.x, this.y, this.z);
        float dotProd = direction.dot(rotationAxis);
        // Shortcut calculation of `projection` requires `direction` to be normalized
        Vector3f projection = direction.duplicate().mul(dotProd);
        ComponentsQuaternion twist = new ComponentsQuaternion(
                projection.getX(), projection.getY(), projection.getZ(), this.w);
        twist.normalise();
        if (dotProd < 0.0) {
            // Ensure `twist` points towards `direction`
            twist.w = -twist.w;
            // Rotation angle `twist.angle()` is now reliable
        }
        return twist;
    }

    @Override
    public Vector3f getAxisOrDefault(Vector3f axis) {
        float sinFromCos = (float) sqrt(Math.max(0, 1 - w * w));
        if (sinFromCos == 0 || Float.isNaN(sinFromCos))
            return axis;

        float x = this.x / sinFromCos;
        float y = this.y / sinFromCos;
        float z = this.z / sinFromCos;

        Vector3f vector = new SimpleVector3f(x, y, z);
        if (vector.lengthSquared() == 0)
            return axis;
        return vector;
    }

    @Override
    public Vector3f getVector() {
        return new SimpleVector3f(x, y, z);
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
    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public void inverse() {
        this.x = -x;
        this.y = -y;
        this.z = -z;
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
    public void normaliseSafely() {
        float magnitude = getMagnitude();
        if (magnitude==0)
            return;
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

        float w = -this.x * otherX - this.y * otherY - this.z * otherZ + this.w * otherW;
        float x = this.x * otherW + this.y * otherZ - this.z * otherY + this.w * otherX;
        float y = -this.x * otherZ + this.y * otherW + this.z * otherX + this.w * otherY;
        float z = this.x * otherY - this.y * otherX + this.z * otherW + this.w * otherZ;

        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public void multiply(Vector3f vec) {
        float wRes = -(x * vec.getX() + y * vec.getY() + z * vec.getZ());
        float xRes = w * vec.getX() + y * vec.getZ() - z * vec.getY();
        float yRes = w * vec.getY() + z * vec.getX() - x * vec.getZ();
        float zRes = w * vec.getZ() + x * vec.getY() - y * vec.getX();
        x = xRes;
        y = yRes;
        z = zRes;
        w = wRes;
    }

    @Override
    public void transform(Vector3f vec) {
        float xx = this.x * this.x, yy = this.y * this.y, zz = this.z * this.z, ww = this.w * this.w;
        float xy = this.x * this.y, xz = this.x * this.z, yz = this.y * this.z, xw = this.x * this.w;
        float zw = this.z * this.w, yw = this.y * this.w, k = 1 / (xx + yy + zz + ww);

        vec.set(Maths.fma((xx - yy - zz + ww) * k, vec.getX(), Maths.fma(2 * (xy - zw) * k, vec.getY(), (2 * (xz + yw) * k) * vec.getZ())),
                Maths.fma(2 * (xy + zw) * k, vec.getX(), Maths.fma((yy - xx - zz + ww) * k, vec.getY(), (2 * (yz - xw) * k) * vec.getZ())),
                Maths.fma(2 * (xz - yw) * k, vec.getX(), Maths.fma(2 * (yz + xw) * k, vec.getY(), ((zz - xx - yy + ww) * k) * vec.getZ())));
    }

    @Override
    public void transformUnit(Vector3f vec) {
        transform(vec);
    }

    @Override
    public void multiplyInv(Vector3f vec) {
        float wRes = -(x * vec.getX() + y * vec.getY() + z * vec.getZ());
        float xRes = w * vec.getX() + z * vec.getY() - y * vec.getZ();
        float yRes = w * vec.getY() + x * vec.getZ() - z * vec.getX();
        float zRes = w * vec.getZ() + y * vec.getX() - x * vec.getY();
        x = xRes;
        y = yRes;
        z = zRes;
        w = wRes;
    }

    @Override
    public void multiply(float s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
        this.w *= s;
    }

    @Override
    public void interpolate(Quaternion quaternionEnd, float ease) {
        double cosHalfTheta = dot(quaternionEnd);

        // if qa=quaternionEnd or qa=-quaternionEnd then theta = 0 and we can return qa
        if (abs(cosHalfTheta) >= 1.0) {
            return;
        }
        // Calculate temporary values.
        double halfTheta = Math.acos(cosHalfTheta);
        double sinHalfTheta = sqrt(1.0 - cosHalfTheta * cosHalfTheta);
        // if theta = 180 degrees then result is not fully defined
        // we could rotate around any axis normal to qa or quaternionEnd
        if (abs(sinHalfTheta) < 0) { // fabs is floating point absolute
            this.w = (w * 0.5f + quaternionEnd.getW() * 0.5f);
            this.x = (x * 0.5f + quaternionEnd.getX() * 0.5f);
            this.y = (y * 0.5f + quaternionEnd.getY() * 0.5f);
            this.z = (z * 0.5f + quaternionEnd.getZ() * 0.5f);
            return;
        }
        double ratioA = Math.sin((1 - ease) * halfTheta) / sinHalfTheta;
        double ratioB = Math.sin(ease * halfTheta) / sinHalfTheta;
        //calculate Quaternion.
        w = (float) (w * ratioA + quaternionEnd.getW() * ratioB);
        x = (float) (x * ratioA + quaternionEnd.getX() * ratioB);
        y = (float) (y * ratioA + quaternionEnd.getY() * ratioB);
        z = (float) (z * ratioA + quaternionEnd.getZ() * ratioB);
    }

    @Override
    public Vector3f velocity(Quaternion other, float delta) {
        Vector3f vel = new SimpleVector3f(
                this.w * other.getX() - this.x * other.getW() - this.y * other.getZ() + this.z * other.getY(),
                this.w * other.getY() + this.x * other.getZ() - this.y * other.getW() - this.z * other.getX(),
                this.w * other.getZ() - this.x * other.getY() + this.y * other.getX() - this.z * other.getW()
        );
        vel.mul((2 / delta));
        return vel;
    }

    @Override
    public float dot(Quaternion other) {
        return x * other.getX() + y * other.getY() + z * other.getZ() + w * other.getW();
    }

    @Override
    public float getMagnitude() {
        return (float) sqrt(getSquaredMagnitude());
    }

    @Override
    public float getSquaredMagnitude() {
        return w * w + x * x + y * y + z * z;
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
    public void copy(Quaternion rotation) {
        this.x = rotation.getX();
        this.y = rotation.getY();
        this.z = rotation.getZ();
        this.w = rotation.getW();
    }

    @Override
    public JavaMathAngle getAngle() {
        return JavaMathAngle.fromRadiant(2 * Math.acos(clamp(w, -1, 1)));
    }

    @Override
    public void copy(Vector4f rotation) {
        this.x = rotation.getX();
        this.y = rotation.getY();
        this.z = rotation.getZ();
        this.w = rotation.getW();
    }

    @Override
    public Angle getAxisAngle(Vector3f axis) {
        axis.copy(getAxisOrDefault(axis));
        return getAngle();
    }

    @Override
    public void add(Quaternion quaternion) {
        this.x += quaternion.getX();
        this.y += quaternion.getY();
        this.z += quaternion.getZ();
        this.w += quaternion.getW();
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
