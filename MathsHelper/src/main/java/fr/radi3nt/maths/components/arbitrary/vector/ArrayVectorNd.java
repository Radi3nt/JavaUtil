package fr.radi3nt.maths.components.arbitrary.vector;

import fr.radi3nt.maths.Maths;
import fr.radi3nt.maths.components.arbitrary.VectorNd;

import java.util.Arrays;

public class ArrayVectorNd implements VectorNd {

    private final double[] vector;

    public ArrayVectorNd(int size) {
        vector = new double[size];
    }

    @Override
    public float length() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public int size() {
        return vector.length;
    }

    @Override
    public void scalar(VectorNd kd) {
        for (int i = 0; i < vector.length; i++) {
            set(i, get(i) * kd.get(i));
        }
    }

    @Override
    public void add(VectorNd damping) {
        for (int i = 0; i < vector.length; i++) {
            set(i, get(i) + damping.get(i));
        }
    }

    public double get(int row) {
        return vector[row];
    }

    public void set(int row, double v) {
        vector[row] = v;
    }

    public void add(int row, double v) {
        vector[row] += v;
    }

    public void div(int row, double v) {
        vector[row] /= v;
    }

    public void clamp(int row, double min, double max) {
        vector[row] = Maths.clamp(vector[row], min, max);
    }

    @Override
    public String toString() {
        return "VectorNd{" +
                "vector=" + Arrays.toString(vector) +
                '}';
    }
}
