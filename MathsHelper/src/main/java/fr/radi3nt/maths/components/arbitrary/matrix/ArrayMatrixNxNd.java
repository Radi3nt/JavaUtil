package fr.radi3nt.maths.components.arbitrary.matrix;

import fr.radi3nt.maths.components.MatrixNxNd;
import fr.radi3nt.maths.components.arbitrary.VectorNd;
import fr.radi3nt.maths.components.arbitrary.vector.ArrayVectorNd;

import java.util.Arrays;
import java.util.BitSet;

public class ArrayMatrixNxNd implements MatrixNxNd {

    private final int width;
    private final int height;
    private final double[][] m;
    private final BitSet zeroSet;

    public ArrayMatrixNxNd(int width, int height) {
        this.width = width;
        this.height = height;
        m = new double[width][height];
        zeroSet = new BitSet(this.width * this.height);
    }

    protected ArrayMatrixNxNd(int width, int height, double[][] m) {
        this.width = width;
        this.height = height;
        this.m = m;
        zeroSet = new BitSet(this.width * this.height);
    }

    @Override
    public ArrayMatrixNxNd multiply(MatrixNxNd matrixNxN) {
        if (this.width != matrixNxN.getHeight())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getHeight() + "!=" + this.width);

        int resultWidth = matrixNxN.getWidth();
        int resultHeight = this.height;

        BitSet nonZero = matrixNxN.nonZero();

        ArrayMatrixNxNd result = new ArrayMatrixNxNd(resultWidth, resultHeight);
        for (int x = 0; x < result.width; x++) {
            for (int i = 0; i < this.width; i++) {
                double cache =  matrixNxN.get(x, i);
                for (int y = 0; y < result.height; y++) {
                    if (nonZero.get(x + i * matrixNxN.getWidth()))
                        result.add(x, y, this.get(i, y) * cache);
                }
            }
        }
        result.markAllNonZero();

        return result;
    }

    @Override
    public ArrayMatrixNxNd multiplyWithTransposed(MatrixNxNd matrixNxN) {
        if (this.width != matrixNxN.getWidth())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getWidth() + "!=" + this.width);

        int resultWidth = matrixNxN.getHeight();
        int resultHeight = this.height;

        BitSet nonZero = matrixNxN.nonZero();

        ArrayMatrixNxNd result = new ArrayMatrixNxNd(resultWidth, resultHeight);
        for (int x = 0; x < result.width; x++) {
            for (int y = 0; y < result.height; y++) {
                double total = 0;
                for (int i = 0; i < this.width; i++) {
                    if (nonZero.get(i + x * matrixNxN.getWidth()))
                        total += this.get(i, y) * matrixNxN.get(i, x);
                }
                result.add(x, y, total);
            }
        }
        result.markAllNonZero();

        return result;
    }

    @Override
    public MatrixNxNd multiplyTransposedOther(MatrixNxNd matrixNxN) {
        if (matrixNxN.getWidth() != this.width)
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getWidth() + "!=" + this.width);

        int resultWidth = this.height;
        int resultHeight = matrixNxN.getHeight();

        BitSet nonZero = this.nonZero();

        ArrayMatrixNxNd result = new ArrayMatrixNxNd(resultWidth, resultHeight);
        for (int x = 0; x < result.width; x++) {
            for (int y = 0; y < result.height; y++) {
                double total = 0;
                for (int i = 0; i < matrixNxN.getWidth(); i++) {
                    if (nonZero.get(i + x * this.getWidth()))
                        total += matrixNxN.get(i, y) * this.get(i, x);
                }
                result.add(x, y, total);
            }
        }
        result.markAllNonZero();

        return result;
    }

    public double[] getWidthArray(int x) {
        return m[x];
    }

    @Override
    public double get(int x, int y) {
        double[] array = m[x];
        return array==null ? 0 : array[y];
    }

    @Override
    public void set(int x, int y, double value) {
        double[] array = m[x];
        if (array==null)
            m[x] = array = new double[this.width];
        array[y] = value;
        zeroSet.set(x + y * this.width, value != 0);
    }

    public void add(int x, int y, double total) {
        double[] array = m[x];
        if (array==null)
            m[x] = array = new double[this.width];
        array[y] += total;
    }

    public void markAllNonZero() {
        zeroSet.set(0, height * width, true);
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
    public ArrayMatrixNxNd duplicate() {
        return new ArrayMatrixNxNd(width, height, deepCopy(m));
    }

    public double[][] getM() {
        return m;
    }

    @Override
    public VectorNd transform(VectorNd vec) {
        VectorNd result = new ArrayVectorNd(height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double value = get(x, y) * vec.get(x);
                result.add(y, value);
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayMatrixNxNd)) return false;

        ArrayMatrixNxNd that = (ArrayMatrixNxNd) o;

        if (width != that.width) return false;
        if (height != that.height) return false;
        return Arrays.deepEquals(m, that.m);
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + Arrays.deepHashCode(m);
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

    public static double[][] deepCopy(double[][] original) {
        if (original == null) {
            return null;
        }

        final double[][] result = new double[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
}
