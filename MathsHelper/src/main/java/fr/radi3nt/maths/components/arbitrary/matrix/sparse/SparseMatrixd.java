package fr.radi3nt.maths.components.arbitrary.matrix.sparse;

import fr.radi3nt.maths.components.MatrixNxNd;
import fr.radi3nt.maths.components.arbitrary.VectorNd;
import fr.radi3nt.maths.components.arbitrary.matrix.ArrayMatrixNxNd;
import fr.radi3nt.maths.components.arbitrary.vector.ArrayVectorNd;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;

public class SparseMatrixd implements MatrixNxNd {

    private final Collection<SparseBlockd> sparseBlocks;
    private int width;
    private int height;

    public SparseMatrixd(Collection<SparseBlockd> sparseBlocks, int width, int height) {
        this.sparseBlocks = sparseBlocks;
        this.width = width;
        this.height = height;
    }


    public SparseMatrixd() {
        this(new ArrayList<>(), 0, 0);
    }

    public void add(SparseBlockd sparseBlock) {
        sparseBlocks.add(sparseBlock);
        computeCursors(sparseBlock);
    }

    private void computeCursors(SparseBlockd sparseBlock) {
        width = Math.max(width, sparseBlock.getStartX() + sparseBlock.getWidth());
        height = Math.max(height, sparseBlock.getStartY() + sparseBlock.getHeight());
    }

    @Override
    public double get(int x, int y) {
        for (SparseBlockd sparseBlock : sparseBlocks) {
            if (sparseBlock.isInBound(x, y)) {
                return sparseBlock.get(x, y);
            }
        }
        return 0;
    }

    @Override
    public void set(int x, int y, double value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public BitSet nonZero() {
        BitSet bitSet = new BitSet(width * height);
        //bitSet.set(0, width*height);

        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int y = sparseBlock.getStartY(); y < sparseBlock.getStartY() + sparseBlock.getHeight(); y++) {
                int row = y * width;
                bitSet.set(sparseBlock.getStartX() + row, sparseBlock.getStartX() + sparseBlock.getWidth() + row);
            }
        }

        return bitSet;
    }

    public VectorNd transform(VectorNd vector) {
        VectorNd result = new ArrayVectorNd(height);
        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int y = sparseBlock.getStartY(); y < sparseBlock.getStartY() + sparseBlock.getHeight(); y++) {
                for (int x = sparseBlock.getStartX(); x < sparseBlock.getStartX() + sparseBlock.getWidth(); x++) {
                    double value = sparseBlock.get(x, y);
                    result.add(y, value * vector.get(x));
                }
            }
        }
        return result;
    }

    public VectorNd transformTransposed(VectorNd vector) {
        VectorNd result = new ArrayVectorNd(width);
        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int y = sparseBlock.getStartY(); y < sparseBlock.getStartY() + sparseBlock.getHeight(); y++) {
                for (int x = sparseBlock.getStartX(); x < sparseBlock.getStartX() + sparseBlock.getWidth(); x++) {
                    double value = sparseBlock.get(x, y);
                    result.add(x, value * vector.get(y));
                }
            }
        }
        return result;
    }

    @Override
    public MatrixNxNd duplicate() {
        return new SparseMatrixd(new ArrayList<>(sparseBlocks), width, height);
    }

    @Override
    public MatrixNxNd multiply(MatrixNxNd matrixNxN) {
        if (this.width != matrixNxN.getHeight())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getHeight() + "!=" + this.width);
        if (matrixNxN instanceof ArrayMatrixNxNd) {
            return multiplyIntern((ArrayMatrixNxNd) matrixNxN);
        }
        return multiplyGeneral(matrixNxN);
    }

    private MatrixNxNd multiplyIntern(ArrayMatrixNxNd matrixNxN) {
        int resultWidth = matrixNxN.getWidth();
        int resultHeight = this.height;

        ArrayMatrixNxNd result = new ArrayMatrixNxNd(resultWidth, resultHeight);
        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int x = 0; x < resultWidth; x++) {
                double[] array = matrixNxN.getWidthArray(x);
                if (array==null)
                    continue;
                for (int i = sparseBlock.getStartX(); i < sparseBlock.getStartX() + sparseBlock.getWidth(); i++) {
                    double temp = array[i];
                    if (temp==0)
                        continue;
                    for (int y = sparseBlock.getStartY(); y < sparseBlock.getStartY() + sparseBlock.getHeight(); y++) {
                        double adding = temp*sparseBlock.get(i, y);
                        if (adding==0)
                            continue;
                        result.add(x, y, adding);
                    }
                }
            }
        }
        result.markAllNonZero();

        return result;
    }

    private ArrayMatrixNxNd multiplyGeneral(MatrixNxNd matrixNxN) {
        int resultWidth = matrixNxN.getWidth();
        int resultHeight = this.height;

        ArrayMatrixNxNd result = new ArrayMatrixNxNd(resultWidth, resultHeight);
        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int x = 0; x < resultWidth; x++) {
                for (int i = sparseBlock.getStartX(); i < sparseBlock.getStartX() + sparseBlock.getWidth(); i++) {
                    double temp = matrixNxN.get(x, i);
                    if (temp==0)
                        continue;
                    for (int y = sparseBlock.getStartY(); y < sparseBlock.getStartY() + sparseBlock.getHeight(); y++) {
                        double adding = temp*sparseBlock.get(i, y);
                        if (adding==0)
                            continue;
                        result.add(x, y, adding);
                    }
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

        ArrayMatrixNxNd result = new ArrayMatrixNxNd(resultWidth, resultHeight);

        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int y = sparseBlock.getStartY(); y < sparseBlock.getStartY() + sparseBlock.getHeight(); y++) {
                for (int x = 0; x < resultWidth; x++) {
                    double total = 0;
                    for (int i = sparseBlock.getStartX(); i < sparseBlock.getStartX() + sparseBlock.getWidth(); i++) {
                        total += sparseBlock.get(i, y) * matrixNxN.get(i, x);
                    }
                    result.add(x, y, total);
                }
            }
        }
        result.markAllNonZero();

        return result;
    }

    @Override
    public MatrixNxNd multiplyTransposedOther(MatrixNxNd matrixNxN) {
        if (matrixNxN.getWidth() != this.width)
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getWidth() + "!=" + this.width);
        if (matrixNxN instanceof ArrayMatrixNxNd)
            return multiplyTransposedOtherIntern((ArrayMatrixNxNd) matrixNxN);
        return multiplyTransposedMatrixOtherGeneral(matrixNxN);
    }

    private MatrixNxNd multiplyTransposedOtherIntern(ArrayMatrixNxNd matrixNxN) {
        int resultWidth = this.height;
        int resultHeight = matrixNxN.getHeight();

        ArrayMatrixNxNd result = new ArrayMatrixNxNd(resultWidth, resultHeight);
        BitSet resultNonZero = result.nonZero();
        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int x = sparseBlock.getStartY(); x < sparseBlock.getStartY() + sparseBlock.getHeight(); x++) {
                for (int i = sparseBlock.getStartX(); i < sparseBlock.getStartX() + sparseBlock.getWidth(); i++) {
                    double cache = sparseBlock.get(i, x);
                    if (cache==0)
                        continue;
                    double[] values = matrixNxN.getWidthArray(i);
                    if (values==null)
                        continue;
                    for (int y = 0; y < resultHeight; y++) {
                        double added = values[y] * cache;
                        if (added==0)
                            continue;
                        result.add(x, y, added);
                        resultNonZero.set(x + y * resultWidth, true);
                    }
                }
            }
        }

        return result;
    }

    private ArrayMatrixNxNd multiplyTransposedMatrixOtherGeneral(MatrixNxNd matrixNxN) {
        int resultWidth = this.height;
        int resultHeight = matrixNxN.getHeight();

        ArrayMatrixNxNd result = new ArrayMatrixNxNd(resultWidth, resultHeight);
        BitSet resultNonZero = result.nonZero();
        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int x = sparseBlock.getStartY(); x < sparseBlock.getStartY() + sparseBlock.getHeight(); x++) {
                for (int i = sparseBlock.getStartX(); i < sparseBlock.getStartX() + sparseBlock.getWidth(); i++) {
                    double cache = sparseBlock.get(i, x);
                    if (cache==0)
                        continue;
                    for (int y = 0; y < resultHeight; y++) {
                        double added = matrixNxN.get(i, y) * cache;
                        if (added==0)
                            continue;
                        result.add(x, y, added);
                        resultNonZero.set(x + y * resultWidth, true);
                    }
                }
            }
        }

        return result;
    }
}
