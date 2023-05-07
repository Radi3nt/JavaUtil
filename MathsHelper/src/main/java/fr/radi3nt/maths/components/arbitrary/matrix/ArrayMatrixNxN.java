package fr.radi3nt.maths.components.arbitrary.matrix;

import fr.radi3nt.maths.components.arbitrary.MatrixNxN;
import fr.radi3nt.maths.components.arbitrary.VectorNf;
import fr.radi3nt.maths.components.arbitrary.vector.ArrayVectorNf;

import java.util.Arrays;
import java.util.BitSet;

public class ArrayMatrixNxN implements MatrixNxN {

    private final int width;
    private final int height;
    private final float[] m;
    private final BitSet zeroSet;

    public ArrayMatrixNxN(int width, int height) {
        this.width = width;
        this.height = height;
        m = new float[width * height];
        zeroSet = new BitSet(this.width * this.height);
    }

    protected ArrayMatrixNxN(int width, int height, float[] m) {
        this.width = width;
        this.height = height;
        this.m = m;
        zeroSet = new BitSet(this.width * this.height);
    }

    @Override
    public ArrayMatrixNxN multiply(MatrixNxN matrixNxN) {
        if (this.width != matrixNxN.getHeight())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getHeight() + "!=" + this.width);

        int resultWidth = matrixNxN.getWidth();
        int resultHeight = this.height;

        BitSet nonZero = matrixNxN.nonZero();

        ArrayMatrixNxN result = new ArrayMatrixNxN(resultWidth, resultHeight);
        for (int x = 0; x < result.width; x++) {
            for (int y = 0; y < result.height; y++) {
                float total = 0;
                for (int i = 0; i < this.width; i++) {
                    if (nonZero.get(x + i * matrixNxN.getWidth()))
                        total += this.get(i, y) * matrixNxN.get(x, i);
                }
                result.set(x, y, total);
            }
        }

        return result;
    }

    @Override
    public ArrayMatrixNxN multiplyTransposed(MatrixNxN matrixNxN) {
        if (this.width != matrixNxN.getWidth())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getWidth() + "!=" + this.width);

        int resultWidth = this.height;
        int resultHeight = matrixNxN.getHeight();

        BitSet nonZero = matrixNxN.nonZero();

        ArrayMatrixNxN result = new ArrayMatrixNxN(resultWidth, resultHeight);
        for (int x = 0; x < result.width; x++) {
            for (int y = 0; y < result.height; y++) {
                float total = 0;
                for (int i = 0; i < this.width; i++) {
                    if (nonZero.get(i + y * matrixNxN.getWidth()))
                        total += this.get(i, x) * matrixNxN.get(i, y);
                }
                result.set(x, y, total);
            }
        }

        return result;
    }

    @Override
    public MatrixNxN multiplyTransposedOther(MatrixNxN matrixNxN) {
        if (this.width != matrixNxN.getWidth())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getWidth() + "!=" + this.width);

        int resultWidth = matrixNxN.getHeight();
        int resultHeight = this.getHeight();

        BitSet nonZero = this.nonZero();

        ArrayMatrixNxN result = new ArrayMatrixNxN(resultWidth, resultHeight);
        for (int x = 0; x < result.width; x++) {
            for (int y = 0; y < result.height; y++) {
                float total = 0;
                for (int i = 0; i < this.width; i++) {
                    if (nonZero.get(i + y * this.getWidth()))
                        total += matrixNxN.get(i, x) * this.get(i, y);
                }
                result.set(x, y, total);
            }
        }

        return result;
    }

    @Override
    public float get(int x, int y) {
        return m[x + y * width];
    }

    @Override
    public void set(int x, int y, float value) {
        m[x + y * width] = value;
        zeroSet.set(x + y * width, value != 0);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public BitSet nonZero() {
        return zeroSet;
    }

    @Override
    public ArrayMatrixNxN duplicate() {
        return new ArrayMatrixNxN(width, height, Arrays.copyOf(m, m.length));
    }

    public float[] getM() {
        return m;
    }

    @Override
    public VectorNf transform(VectorNf vec) {
        VectorNf result = new ArrayVectorNf(height);

        for (int y = 0; y < height; y++) {
            float yi = 0;
            for (int x = 0; x < width; x++) {
                yi += get(x, y) * vec.get(x);
            }
            result.set(y, yi);
        }

        return result;
    }

    @Override
    public String toString() {
        return "ArrayMatrixNxN{" +
                "width=" + width +
                ", height=" + height +
                ", m=" + Arrays.toString(m) +
                '}';
    }
}
