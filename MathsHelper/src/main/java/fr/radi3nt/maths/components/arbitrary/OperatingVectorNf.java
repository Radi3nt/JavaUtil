package fr.radi3nt.maths.components.arbitrary;

public interface OperatingVectorNf {

    float get(int row);

    OperatingVectorNf duplicate();

    OperatingVectorNf div(float number);
    OperatingVectorNf mul(float number);

    OperatingVectorNf add(OperatingVectorNf other);
    OperatingVectorNf sub(OperatingVectorNf other);

    int size();

    float length();
}
