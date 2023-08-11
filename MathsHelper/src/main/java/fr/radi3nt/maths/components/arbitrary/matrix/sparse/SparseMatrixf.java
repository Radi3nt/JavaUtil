package fr.radi3nt.maths.components.arbitrary.matrix.sparse;

import fr.radi3nt.maths.components.arbitrary.MatrixNxNf;
import fr.radi3nt.maths.components.arbitrary.VectorNf;
import fr.radi3nt.maths.components.arbitrary.matrix.ArrayMatrixNxNf;
import fr.radi3nt.maths.components.arbitrary.vector.ArrayVectorNf;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;

public class SparseMatrixf implements MatrixNxNf {

    private final Collection<SparseBlockf> sparseBlocks;
    private int width;
    private int height;

    public SparseMatrixf(Collection<SparseBlockf> sparseBlocks, int width, int height) {
        this.sparseBlocks = sparseBlocks;
        this.width = width;
        this.height = height;
    }


    public SparseMatrixf() {
        this(new ArrayList<>(), 0, 0);
    }

    public void add(SparseBlockf sparseBlock) {
        sparseBlocks.add(sparseBlock);
        computeCursors(sparseBlock);
    }

    private void computeCursors(SparseBlockf sparseBlock) {
        width = Math.max(width, sparseBlock.getStartI() + sparseBlock.getLengthI());
        height = Math.max(height, sparseBlock.getStartJ() + sparseBlock.getLengthJ());
    }

    @Override
    public float get(int x, int y) {
        for (SparseBlockf sparseBlock : sparseBlocks) {
            if (sparseBlock.isInBound(x, y)) {
                return sparseBlock.get(x, y);
            }
        }
        return 0;
    }

    @Override
    public void set(int x, int y, float value) {
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

        for (SparseBlockf sparseBlock : sparseBlocks) {
            for (int j = sparseBlock.getStartJ(); j < sparseBlock.getStartJ() + sparseBlock.getLengthJ(); j++) {
                int row = j * width;
                bitSet.set(sparseBlock.getStartI() + row, sparseBlock.getStartI() + sparseBlock.getLengthI() + row);
            }
        }

        return bitSet;
    }

    @Override
    public float[] getM() {
        throw new UnsupportedOperationException();
    }

    public VectorNf transform(VectorNf vector) {
        VectorNf result = new ArrayVectorNf(height);
        for (SparseBlockf sparseBlock : sparseBlocks) {
            for (int j = sparseBlock.getStartJ(); j < sparseBlock.getStartJ() + sparseBlock.getLengthJ(); j++) {
                for (int i = sparseBlock.getStartI(); i < sparseBlock.getStartI() + sparseBlock.getLengthI(); i++) {
                    float value = sparseBlock.get(i, j);
                    result.add(j, value * vector.get(i));
                }
            }
        }
        return result;
    }

    public VectorNf transformTransposed(VectorNf vector) {
        VectorNf result = new ArrayVectorNf(width);
        for (SparseBlockf sparseBlock : sparseBlocks) {
            for (int j = sparseBlock.getStartJ(); j < sparseBlock.getStartJ() + sparseBlock.getLengthJ(); j++) {
                for (int i = sparseBlock.getStartI(); i < sparseBlock.getStartI() + sparseBlock.getLengthI(); i++) {
                    float value = sparseBlock.get(i, j);
                    result.add(i, value * vector.get(j));
                }
            }
        }
        return result;
    }

    @Override
    public MatrixNxNf duplicate() {
        return new SparseMatrixf(new ArrayList<>(sparseBlocks), width, height);
    }

    @Override
    public MatrixNxNf multiply(MatrixNxNf matrixNxN) {
        if (this.width != matrixNxN.getHeight())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getHeight() + "!=" + this.width);

        int resultWidth = matrixNxN.getWidth();
        int resultHeight = this.height;

        ArrayMatrixNxNf result = new ArrayMatrixNxNf(resultWidth, resultHeight);

        for (SparseBlockf sparseBlock : sparseBlocks) {
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
    public ArrayMatrixNxNf multiplyTransposed(MatrixNxNf matrixNxN) {
        if (this.width != matrixNxN.getWidth())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getWidth() + "!=" + this.width);

        int resultWidth = this.height;
        int resultHeight = matrixNxN.getHeight();

        ArrayMatrixNxNf result = new ArrayMatrixNxNf(resultWidth, resultHeight);

        for (SparseBlockf sparseBlock : sparseBlocks) {
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
    public MatrixNxNf multiplyTransposedOther(MatrixNxNf matrixNxN) {
        if (this.width != matrixNxN.getWidth())
            throw new UnsupportedOperationException("Unable to multiply matrices: " + matrixNxN.getWidth() + "!=" + this.width);

        int resultWidth = matrixNxN.getHeight();
        int resultHeight = this.getHeight();

        ArrayMatrixNxNf result = new ArrayMatrixNxNf(resultWidth, resultHeight);


        for (SparseBlockf sparseBlock : sparseBlocks) {
            for (int y = sparseBlock.getStartJ(); y < sparseBlock.getStartJ() + sparseBlock.getLengthJ(); y++) {
                for (int x = sparseBlock.getStartI(); x < sparseBlock.getStartI() + sparseBlock.getLengthI(); x++) {
                    for (int i = 0; i < matrixNxN.getHeight(); i++) {
                        float total = matrixNxN.get(x, i) * sparseBlock.get(x, y);
                        result.set(y, i, result.get(y, i) + total);
                    }
                }
            }
        }

        return result;
    }
}
