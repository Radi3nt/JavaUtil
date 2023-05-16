package fr.radi3nt.maths.components;

import fr.radi3nt.maths.components.arbitrary.VectorNd;

import java.util.BitSet;

public interface MatrixNxNd {

    double get(int x, int y);

    void set(int x, int y, double value);

    int getWidth();

    int getHeight();

    BitSet nonZero();

    double[] getM();

    MatrixNxNd multiply(MatrixNxNd matrixNxN);

    MatrixNxNd multiplyTransposed(MatrixNxNd matrixNxN);

    MatrixNxNd multiplyTransposedOther(MatrixNxNd matrixNxN);

    VectorNd transform(VectorNd vec);

    MatrixNxNd duplicate();

}
