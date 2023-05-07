package fr.radi3nt.maths.components.arbitrary;

import java.util.BitSet;

public interface MatrixNxN {

    float get(int x, int y);

    void set(int x, int y, float value);

    int getWidth();

    int getHeight();

    BitSet nonZero();

    float[] getM();

    MatrixNxN multiply(MatrixNxN matrixNxN);

    MatrixNxN multiplyTransposed(MatrixNxN matrixNxN);

    MatrixNxN multiplyTransposedOther(MatrixNxN matrixNxN);

    VectorNf transform(VectorNf vec);

    MatrixNxN duplicate();

}
