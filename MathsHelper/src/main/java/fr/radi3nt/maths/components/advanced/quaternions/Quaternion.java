package fr.radi3nt.maths.components.advanced.quaternions;

public interface Quaternion {

    float getX();

    float getY();

    float getZ();

    float getW();

    void normalise();

    void multiply(Quaternion quaternion);

    float getMagnitude();

    Quaternion getConjugate();

    Quaternion duplicate();

}
