package fr.radi3nt.maths.components.advanced.quaternions;

import fr.radi3nt.maths.components.vectors.Vector3f;

public interface Quaternion {

    float getX();

    float getY();

    float getZ();

    float getW();

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

    float dot(Quaternion other);

    float getMagnitude();

    Quaternion getConjugate();

    Quaternion duplicate();

    void copy(Quaternion rotation);

    Vector3f getAxis();

    void setX(float x);
    void setY(float y);
    void setZ(float z);
}
