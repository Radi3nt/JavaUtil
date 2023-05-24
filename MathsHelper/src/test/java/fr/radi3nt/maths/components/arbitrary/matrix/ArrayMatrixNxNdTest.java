package fr.radi3nt.maths.components.arbitrary.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ArrayMatrixNxNdTest {

    private static final ArrayMatrixNxNd resultOneRow = new ArrayMatrixNxNd(3, 4);
    private static final ArrayMatrixNxNd resultMultiple = new ArrayMatrixNxNd(2, 3);

    @BeforeAll
    static void setUp() {
        resultOneRow.set(0, 0, 1);
        resultOneRow.set(0, 1, 2);
        resultOneRow.set(0, 2, 3);
        resultOneRow.set(0, 3, 4);

        resultOneRow.set(1, 0, 2);
        resultOneRow.set(1, 1, 4);
        resultOneRow.set(1, 2, 6);
        resultOneRow.set(1, 3, 8);

        resultOneRow.set(2, 0, 3);
        resultOneRow.set(2, 1, 6);
        resultOneRow.set(2, 2, 9);
        resultOneRow.set(2, 3, 12);

        resultMultiple.set(0, 0, 22);
        resultMultiple.set(1, 0, 28);

        resultMultiple.set(0, 1, 49);
        resultMultiple.set(1, 1, 64);

        resultMultiple.set(0, 2, 76);
        resultMultiple.set(1, 2, 100);
    }

    @Test
    void multiplyOneRow() {
        ArrayMatrixNxNd arrayMatrixNxNd = new ArrayMatrixNxNd(1, 4);
        arrayMatrixNxNd.set(0, 0, 1);
        arrayMatrixNxNd.set(0, 1, 2);
        arrayMatrixNxNd.set(0, 2, 3);
        arrayMatrixNxNd.set(0, 3, 4);
        ArrayMatrixNxNd arrayMatrixNxNd1 = new ArrayMatrixNxNd(3, 1);
        arrayMatrixNxNd1.set(0, 0, 1);
        arrayMatrixNxNd1.set(1, 0, 2);
        arrayMatrixNxNd1.set(2, 0, 3);
        Assertions.assertEquals(arrayMatrixNxNd.multiply(arrayMatrixNxNd1), resultOneRow);
    }

    @Test
    void multiplyMultiple() {
        ArrayMatrixNxNd arrayMatrixNxNd = new ArrayMatrixNxNd(3, 3);
        arrayMatrixNxNd.set(0, 0, 1);
        arrayMatrixNxNd.set(1, 0, 2);
        arrayMatrixNxNd.set(2, 0, 3);

        arrayMatrixNxNd.set(0, 1, 4);
        arrayMatrixNxNd.set(1, 1, 5);
        arrayMatrixNxNd.set(2, 1, 6);

        arrayMatrixNxNd.set(0, 2, 7);
        arrayMatrixNxNd.set(1, 2, 8);
        arrayMatrixNxNd.set(2, 2, 9);

        ArrayMatrixNxNd arrayMatrixNxNd1 = new ArrayMatrixNxNd(2, 3);
        arrayMatrixNxNd1.set(0, 0, 1);
        arrayMatrixNxNd1.set(1, 0, 2);

        arrayMatrixNxNd1.set(0, 1, 3);
        arrayMatrixNxNd1.set(1, 1, 4);

        arrayMatrixNxNd1.set(0, 2, 5);
        arrayMatrixNxNd1.set(1, 2, 6);

        Assertions.assertEquals(arrayMatrixNxNd.multiply(arrayMatrixNxNd1), resultMultiple);
    }

    @Test
    void multiplyWithTransposedOneRow() {
        ArrayMatrixNxNd arrayMatrixNxNd = new ArrayMatrixNxNd(1, 4);
        arrayMatrixNxNd.set(0, 0, 1);
        arrayMatrixNxNd.set(0, 1, 2);
        arrayMatrixNxNd.set(0, 2, 3);
        arrayMatrixNxNd.set(0, 3, 4);
        ArrayMatrixNxNd arrayMatrixNxNd1 = new ArrayMatrixNxNd(1, 3);
        arrayMatrixNxNd1.set(0, 0, 1);
        arrayMatrixNxNd1.set(0, 1, 2);
        arrayMatrixNxNd1.set(0, 2, 3);
        Assertions.assertEquals(arrayMatrixNxNd.multiplyWithTransposed(arrayMatrixNxNd1), resultOneRow);
    }

    @Test
    void multiplyWithTransposedMultiple() {
        ArrayMatrixNxNd arrayMatrixNxNd = new ArrayMatrixNxNd(3, 3);
        arrayMatrixNxNd.set(0, 0, 1);
        arrayMatrixNxNd.set(1, 0, 2);
        arrayMatrixNxNd.set(2, 0, 3);

        arrayMatrixNxNd.set(0, 1, 4);
        arrayMatrixNxNd.set(1, 1, 5);
        arrayMatrixNxNd.set(2, 1, 6);

        arrayMatrixNxNd.set(0, 2, 7);
        arrayMatrixNxNd.set(1, 2, 8);
        arrayMatrixNxNd.set(2, 2, 9);

        ArrayMatrixNxNd arrayMatrixNxNd1 = new ArrayMatrixNxNd(3, 2);
        arrayMatrixNxNd1.set(0, 0, 1);
        arrayMatrixNxNd1.set(0, 1, 2);

        arrayMatrixNxNd1.set(1, 0, 3);
        arrayMatrixNxNd1.set(1, 1, 4);

        arrayMatrixNxNd1.set(2, 0, 5);
        arrayMatrixNxNd1.set(2, 1, 6);

        Assertions.assertEquals(arrayMatrixNxNd.multiplyWithTransposed(arrayMatrixNxNd1), resultMultiple);
    }

    @Test
    void multiplyWithTransposedOtherOneRow() {
        ArrayMatrixNxNd arrayMatrixNxNd = new ArrayMatrixNxNd(1, 4);
        arrayMatrixNxNd.set(0, 0, 1);
        arrayMatrixNxNd.set(0, 1, 2);
        arrayMatrixNxNd.set(0, 2, 3);
        arrayMatrixNxNd.set(0, 3, 4);
        ArrayMatrixNxNd arrayMatrixNxNd1 = new ArrayMatrixNxNd(1, 3);
        arrayMatrixNxNd1.set(0, 0, 1);
        arrayMatrixNxNd1.set(0, 1, 2);
        arrayMatrixNxNd1.set(0, 2, 3);
        Assertions.assertEquals(arrayMatrixNxNd1.multiplyTransposedOther(arrayMatrixNxNd), resultOneRow);
    }

    @Test
    void multiplyWithTransposedOtherMultiple() {
        ArrayMatrixNxNd arrayMatrixNxNd = new ArrayMatrixNxNd(3, 3);
        arrayMatrixNxNd.set(0, 0, 1);
        arrayMatrixNxNd.set(1, 0, 2);
        arrayMatrixNxNd.set(2, 0, 3);

        arrayMatrixNxNd.set(0, 1, 4);
        arrayMatrixNxNd.set(1, 1, 5);
        arrayMatrixNxNd.set(2, 1, 6);

        arrayMatrixNxNd.set(0, 2, 7);
        arrayMatrixNxNd.set(1, 2, 8);
        arrayMatrixNxNd.set(2, 2, 9);

        ArrayMatrixNxNd arrayMatrixNxNd1 = new ArrayMatrixNxNd(3, 2);
        arrayMatrixNxNd1.set(0, 0, 1);
        arrayMatrixNxNd1.set(0, 1, 2);

        arrayMatrixNxNd1.set(1, 0, 3);
        arrayMatrixNxNd1.set(1, 1, 4);

        arrayMatrixNxNd1.set(2, 0, 5);
        arrayMatrixNxNd1.set(2, 1, 6);
        Assertions.assertEquals(arrayMatrixNxNd1.multiplyTransposedOther(arrayMatrixNxNd), resultMultiple);
    }

}