package fr.radi3nt.maths.components.arbitrary.matrix.sparse;

public class SparseBlockd {

    private final int startI;
    private final int startJ;
    private final int lengthI;
    private final int lengthJ;

    private final double[] values;

    public SparseBlockd(int startI, int startJ, int lengthI, int lengthJ, double[] values) {
        this.startI = startI;
        this.startJ = startJ;
        this.lengthI = lengthI;
        this.lengthJ = lengthJ;
        this.values = values;
    }

    public SparseBlockd(int startI, int startJ, int lengthI, int lengthJ, double[][] m) {
        this.startI = startI;
        this.startJ = startJ;
        this.lengthI = lengthI;
        this.lengthJ = lengthJ;
        this.values = new double[]{lengthI * lengthJ};

        for (int i = 0; i < lengthI; i++) {
            for (int j = 0; j < lengthJ; j++) {
                values[i + j * lengthI] = m[i][j];
            }
        }
    }

    public double get(int i, int j) {
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

    public double[] getValues() {
        return values;
    }

    public boolean isInBound(int x, int y) {
        return x >= startI && y >= startJ && x < startI + lengthI && y < startJ + lengthJ;
    }
}
