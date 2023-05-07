package fr.radi3nt.maths.components.arbitrary.vector;

import fr.radi3nt.maths.Maths;
import fr.radi3nt.maths.components.arbitrary.VectorNf;

import java.util.ArrayList;
import java.util.List;

public class ExtensibleVectorNf implements VectorNf {

    private final List<Float> vector;

    public ExtensibleVectorNf() {
        vector = new ArrayList<>();
    }

    @Override
    public float length() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public int size() {
        return vector.size();
    }

    public float get(int row) {
        return vector.get(row);
    }

    public void set(int row, float v) {
        if (row >= vector.size())
            vector.add(row, v);
        else
            vector.set(row, v);
    }

    public void add(float v) {
        vector.add(v);
    }

    public void add(int row, float v) {
        vector.set(row, getOrZero(row) + v);
    }

    private Float getOrZero(int row) {
        return row < vector.size() ? get(row) : 0;
    }

    public void div(int row, float v) {
        vector.set(row, getOrZero(row) / v);
    }

    public void clamp(int row, float min, float max) {
        vector.set(row, Maths.clamp(getOrZero(row), min, max));
    }

    @Override
    public String toString() {
        return "ExtensibleVectorNf{" +
                "vector=" + vector +
                '}';
    }
}
