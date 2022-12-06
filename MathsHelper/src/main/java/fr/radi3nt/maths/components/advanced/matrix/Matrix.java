package fr.radi3nt.maths.components.advanced.matrix;

public interface Matrix {

    void identity();

    void zero();

    void transpose();

    void invert();

    float get(int x, int y);

    void set(int x, int y, float value);

    Matrix duplicate();

}
