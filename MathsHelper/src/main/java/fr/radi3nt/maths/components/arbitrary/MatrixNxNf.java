package fr.radi3nt.maths.components.arbitrary;

import java.util.BitSet;

public interface MatrixNxNf {

    float get(int x, int y);

    void set(int x, int y, float value);

    int getWidth();

    int getHeight();

    BitSet nonZero();

    float[] getM();

    MatrixNxNf multiply(MatrixNxNf matrixNxN);

    MatrixNxNf multiplyTransposed(MatrixNxNf matrixNxN);

    MatrixNxNf multiplyTransposedOther(MatrixNxNf matrixNxN);

    VectorNf transform(VectorNf vec);

    MatrixNxNf duplicate();

}
