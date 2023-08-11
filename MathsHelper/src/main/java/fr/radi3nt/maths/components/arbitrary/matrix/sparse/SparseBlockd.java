package fr.radi3nt.maths.components.arbitrary.matrix.sparse;

public class SparseBlockd {

    private final int startX;
    private final int startY;
    private final int width;
    private final int height;

    private final double[] values;

    public SparseBlockd(int startX, int startY, int width, int height, double[] values) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.values = values;
    }

    public SparseBlockd(int startX, int startY, int width, int height, double[][] m) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.values = new double[]{width * height};

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                values[i + j * width] = m[i][j];
            }
        }
    }

    public double get(int i, int j) {
        return values[(i - startX) + (j - startY) * width];
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double[] getValues() {
        return values;
    }

    public boolean isInBound(int x, int y) {
        return isInBoundX(x) && isInBoundY(y);
    }

    protected boolean isInBoundY(int y) {
        return y >= startY && y < startY + height;
    }

    protected boolean isInBoundX(int x) {
        return x >= startX && x < startX + width;
    }
}
