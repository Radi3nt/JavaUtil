package fr.radi3nt.maths.components;

import fr.radi3nt.maths.components.arbitrary.VectorNd;

import java.util.BitSet;

public interface MatrixNxNd {

    double get(int x, int y);

    void set(int x, int y, double value);

    int getWidth();

    int getHeight();

    BitSet nonZero();

    MatrixNxNd multiply(MatrixNxNd matrixNxN);

    MatrixNxNd multiplyWithTransposed(MatrixNxNd matrixNxN);

    MatrixNxNd multiplyTransposedOther(MatrixNxNd matrixNxN);

    VectorNd transform(VectorNd vec);

    MatrixNxNd duplicate();

}
