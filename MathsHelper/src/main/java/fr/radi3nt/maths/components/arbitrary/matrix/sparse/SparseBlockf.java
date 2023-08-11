package fr.radi3nt.maths.components.arbitrary.matrix.sparse;

public class SparseBlockf {

    private final int startI;
    private final int startJ;
    private final int lengthI;
    private final int lengthJ;

    private final float[] values;

    public SparseBlockf(int startI, int startJ, int lengthI, int lengthJ, float[] values) {
        this.startI = startI;
        this.startJ = startJ;
        this.lengthI = lengthI;
        this.lengthJ = lengthJ;
        this.values = values;
    }

    public SparseBlockf(int startI, int startJ, int lengthI, int lengthJ, float[][] m) {
        this.startI = startI;
        this.startJ = startJ;
        this.lengthI = lengthI;
        this.lengthJ = lengthJ;
        this.values = new float[]{lengthI * lengthJ};

        for (int i = 0; i < lengthI; i++) {
            for (int j = 0; j < lengthJ; j++) {
                values[i + j * lengthI] = m[i][j];
            }
        }
    }

    public float get(int i, int j) {
        return values[(i - startI) + (j - startJ) * lengthI];
    }

    public int getStartI() {
        return startI;
    }

    public int getStartJ() {
        return startJ;
    }

    public int getLengthI() {
        return lengthI;
    }

    public int getLengthJ() {
        return lengthJ;
    }

    public float[] getValues() {
        return values;
    }

    public boolean isInBound(int x, int y) {
        return x >= startI && y >= startJ && x < startI + lengthI && y < startJ + lengthJ;
    }
}
