package fr.radi3nt.maths.components.arbitrary.vector;

import fr.radi3nt.maths.Maths;
import fr.radi3nt.maths.components.arbitrary.VectorNd;

import java.util.ArrayList;
import java.util.List;

public class ExtensibleVectorNd implements VectorNd {

    private final List<Double> vector;

    public ExtensibleVectorNd() {
        vector = new ArrayList<>();
    }

    @Override
    public float length() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public int size() {
        return vector.size();
    }

    @Override
    public void scalar(VectorNd kd) {
        for (int i = 0; i < vector.size(); i++) {
            set(i, get(i) * kd.get(i));
        }
    }

    public double get(int row) {
        return vector.get(row);
    }

    public void set(int row, double v) {
        if (row >= vector.size())
            vector.add(row, v);
        else
            vector.set(row, v);
    }

    public void add(double v) {
        vector.add(v);
    }

    public void add(int row, double v) {
        vector.set(row, getOrZero(row) + v);
    }

    private Double getOrZero(int row) {
        return row < vector.size() ? get(row) : 0;
    }

    public void div(int row, double v) {
        vector.set(row, getOrZero(row) / v);
    }

    public void clamp(int row, double min, double max) {
        vector.set(row, Maths.clamp(getOrZero(row), min, max));
    }

    @Override
    public String toString() {
        return "ExtensibleVectorNd{" +
                "vector=" + vector +
                '}';
    }
}
