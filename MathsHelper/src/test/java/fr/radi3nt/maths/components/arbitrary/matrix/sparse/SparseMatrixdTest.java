package fr.radi3nt.maths.components.arbitrary.matrix.sparse;

import org.junit.jupiter.api.Test;

class SparseMatrixdTest {

    @Test
    void multiply() {
        SparseMatrixd sparseMatrixd = new SparseMatrixd();
        sparseMatrixd.add(new SparseBlockd(0, 0, 1, 1, new double[]{1}));
        sparseMatrixd.add(new SparseBlockd(0, 1, 1, 1, new double[]{2}));
        sparseMatrixd.add(new SparseBlockd(0, 2, 1, 1, new double[]{3}));
        sparseMatrixd.add(new SparseBlockd(0, 3, 1, 1, new double[]{4}));

        SparseMatrixd sparseMatrixd1 = new SparseMatrixd();
        sparseMatrixd1.add(new SparseBlockd(0, 0, 1, 1, new double[]{1}));
        sparseMatrixd1.add(new SparseBlockd(1, 0, 1, 1, new double[]{2}));
        sparseMatrixd1.add(new SparseBlockd(2, 0, 1, 1, new double[]{3}));

        System.out.println(sparseMatrixd.multiply(sparseMatrixd1));
    }
}