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
        width = Math.max(width, sparseBlock.getStartI() + sparseBlock.getLengthI());
        height = Math.max(height, sparseBlock.getStartJ() + sparseBlock.getLengthJ());
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
            for (int j = sparseBlock.getStartJ(); j < sparseBlock.getStartJ() + sparseBlock.getLengthJ(); j++) {
                int row = j * width;
                bitSet.set(sparseBlock.getStartI() + row, sparseBlock.getStartI() + sparseBlock.getLengthI() + row);
            }
        }

        return bitSet;
    }

    @Override
    public double[] getM() {
        throw new UnsupportedOperationException();
    }

    public VectorNd transform(VectorNd vector) {
        VectorNd result = new ArrayVectorNd(height);
        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int j = sparseBlock.getStartJ(); j < sparseBlock.getStartJ() + sparseBlock.getLengthJ(); j++) {
                for (int i = sparseBlock.getStartI(); i < sparseBlock.getStartI() + sparseBlock.getLengthI(); i++) {
                    double value = sparseBlock.get(i, j);
                    result.add(j, value * vector.get(i));
                }
            }
        }
        return result;
    }

    public VectorNd transformTransposed(VectorNd vector) {
        VectorNd result = new ArrayVectorNd(width);
        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int j = sparseBlock.getStartJ(); j < sparseBlock.getStartJ() + sparseBlock.getLengthJ(); j++) {
                for (int i = sparseBlock.getStartI(); i < sparseBlock.getStartI() + sparseBlock.getLengthI(); i++) {
                    double value = sparseBlock.get(i, j);
                    result.add(i, value * vector.get(j));
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

        int resultWidth = matrixNxN.getWidth();
        int resultHeight = this.height;

        ArrayMatrixNxNd result = new ArrayMatrixNxNd(resultWidth, resultHeight);

        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int y = sparseBlock.getStartJ(); y < sparseBlock.getStartJ() + sparseBlock.getLengthJ(); y++) {
                for (int x = sparseBlock.getStartI(); x < sparseBlock.getStartI() + sparseBlock.getLengthI(); x++) {
                    float total = 0;
                    for (int i = sparseBlock.getStartI(); i < sparseBlock.getStartI() + sparseBlock.getLengthI(); i++) {
                        total += sparseBlock.get(i, y) * matrixNxN.get(x, i);
                    }
                    result.set(x, y, total);
                }
            }
        }

        /*

        for (int x = 0; x < result.getWidth(); x++) {
            for (int y = 0; y < result.getHeight(); y++) {
                float total = 0;
                for (int i = 0; i < this.width; i++) {
                    total+=this.get(i, y) * matrixNxN.get(x, i);
                }
                result.set(x, y, total);
            }
        }
         */

        return result;
    }

    @Override
    public ArrayMatrixNxNd multiplyTransposed(MatrixNxNd matrixNxN) {
        if (this.width != matrixNxN.getWidth())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getWidth() + "!=" + this.width);

        int resultWidth = this.height;
        int resultHeight = matrixNxN.getHeight();

        ArrayMatrixNxNd result = new ArrayMatrixNxNd(resultWidth, resultHeight);

        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int y = sparseBlock.getStartJ(); y < sparseBlock.getStartJ() + sparseBlock.getLengthJ(); y++) {
                for (int x = sparseBlock.getStartI(); x < sparseBlock.getStartI() + sparseBlock.getLengthI(); x++) {
                    float total = 0;
                    for (int i = sparseBlock.getStartI(); i < sparseBlock.getStartI() + sparseBlock.getLengthI(); i++) {
                        total += sparseBlock.get(i, x) *
                                matrixNxN.get(i, y);
                    }
                    result.set(x, y, total);
                }
            }
        }

        /*
        ArrayMatrixNxN result = new ArrayMatrixNxN(resultWidth, resultHeight);
        for (int x = 0; x < result.getWidth(); x++) {
            for (int y = 0; y < result.getHeight(); y++) {
                float total = 0;
                for (int i = 0; i < this.width; i++) {
                    total+=this.get(i, x) *
                            matrixNxN.get(i, y);
                }
                result.set(x, y, total);
            }
        }

         */

        return result;
    }

    @Override
    public MatrixNxNd multiplyTransposedOther(MatrixNxNd matrixNxN) {
        if (this.width != matrixNxN.getWidth())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getWidth() + "!=" + this.width);

        int resultWidth = matrixNxN.getHeight();
        int resultHeight = this.getHeight();

        ArrayMatrixNxNd result = new ArrayMatrixNxNd(resultWidth, resultHeight);

        for (SparseBlockd sparseBlock : sparseBlocks) {
            for (int y = sparseBlock.getStartJ(); y < sparseBlock.getStartJ() + sparseBlock.getLengthJ(); y++) {
                for (int x = sparseBlock.getStartI(); x < sparseBlock.getStartI() + sparseBlock.getLengthI(); x++) {
                    for (int i = 0; i < matrixNxN.getHeight(); i++) {
                        double total = matrixNxN.get(x, i) * sparseBlock.get(x, y);
                        result.set(y, i, result.get(y, i) + total);
                    }
                }
            }
        }

        return result;
    }
}
