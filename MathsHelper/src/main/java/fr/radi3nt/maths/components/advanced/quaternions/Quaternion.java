package fr.radi3nt.maths.components.advanced.quaternions;

import fr.radi3nt.maths.components.advanced.matrix.angle.Angle;
import fr.radi3nt.maths.components.vectors.Vector3f;

public interface Quaternion {

    float getX();

    void setX(float x);

    float getY();

    void setY(float y);

    float getZ();

    void setZ(float z);

    float getW();

    void setW(float w);

    void inverse();

    void normalise();

    void add(Quaternion quaternion);

    void multiply(Quaternion quaternion);

    void multiply(Vector3f vec);

    void transform(Vector3f vec);

    void transformUnit(Vector3f result);

    void multiplyInv(Vector3f vec);

    void multiply(float s);

    void interpolate(Quaternion quaternionEnd, float ease);

    Vector3f velocity(Quaternion other, float delta);

    float dot(Quaternion other);

    float getMagnitude();
    float getSquaredMagnitude();

    Quaternion getConjugate();

    Quaternion duplicate();

    void copy(Quaternion rotation);

    Vector3f getAxisOrDefault(Vector3f axis);

    Vector3f getVector();

    Angle getAngle();
}
