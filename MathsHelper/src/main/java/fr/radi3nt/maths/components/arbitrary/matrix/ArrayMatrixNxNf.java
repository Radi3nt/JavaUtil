package fr.radi3nt.maths.components.arbitrary.matrix;

import fr.radi3nt.maths.components.arbitrary.MatrixNxNf;
import fr.radi3nt.maths.components.arbitrary.VectorNf;
import fr.radi3nt.maths.components.arbitrary.vector.ArrayVectorNf;

import java.util.Arrays;
import java.util.BitSet;

public class ArrayMatrixNxNf implements MatrixNxNf {

    private final int width;
    private final int height;
    private final float[] m;
    private final BitSet zeroSet;

    public ArrayMatrixNxNf(int width, int height) {
        this.width = width;
        this.height = height;
        m = new float[width * height];
        zeroSet = new BitSet(this.width * this.height);
    }

    protected ArrayMatrixNxNf(int width, int height, float[] m) {
        this.width = width;
        this.height = height;
        this.m = m;
        zeroSet = new BitSet(this.width * this.height);
    }

    @Override
    public ArrayMatrixNxNf multiply(MatrixNxNf matrixNxN) {
        if (this.width != matrixNxN.getHeight())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getHeight() + "!=" + this.width);

        int resultWidth = matrixNxN.getWidth();
        int resultHeight = this.height;

        BitSet nonZero = matrixNxN.nonZero();

        ArrayMatrixNxNf result = new ArrayMatrixNxNf(resultWidth, resultHeight);
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
    public ArrayMatrixNxNf multiplyTransposed(MatrixNxNf matrixNxN) {
        if (this.width != matrixNxN.getWidth())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getWidth() + "!=" + this.width);

        int resultWidth = this.height;
        int resultHeight = matrixNxN.getHeight();

        BitSet nonZero = matrixNxN.nonZero();

        ArrayMatrixNxNf result = new ArrayMatrixNxNf(resultWidth, resultHeight);
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
    public MatrixNxNf multiplyTransposedOther(MatrixNxNf matrixNxN) {
        if (this.width != matrixNxN.getWidth())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getWidth() + "!=" + this.width);

        int resultWidth = matrixNxN.getHeight();
        int resultHeight = this.getHeight();

        BitSet nonZero = this.nonZero();

        ArrayMatrixNxNf result = new ArrayMatrixNxNf(resultWidth, resultHeight);
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
    public ArrayMatrixNxNf duplicate() {
        return new ArrayMatrixNxNf(width, height, Arrays.copyOf(m, m.length));
    }

    public float[] getM() {
        return m;
    }

    @Override
    public VectorNf transform(VectorNf vec) {
        VectorNf result = new ArrayVectorNf(height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float value = get(x, y) * vec.get(x);
                result.add(y, value);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ArrayMatrixNxN{" +
                "\n");
        stringBuilder.append("width=").append(width).append("\n");
        stringBuilder.append("height=").append(height).append("\n");

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                stringBuilder.append(get(i, j)).append(" ");
            }
            stringBuilder.append("\n");
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
