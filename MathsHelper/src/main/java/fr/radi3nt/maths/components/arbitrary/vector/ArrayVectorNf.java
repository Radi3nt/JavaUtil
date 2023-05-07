package fr.radi3nt.maths.components.arbitrary.vector;

import fr.radi3nt.maths.Maths;
import fr.radi3nt.maths.components.arbitrary.VectorNf;

import java.util.Arrays;

public class ArrayVectorNf implements VectorNf {

    private final float[] vector;

    public ArrayVectorNf(int size) {
        vector = new float[size];
    }

    @Override
    public float length() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public int size() {
        return vector.length;
    }

    public float get(int row) {
        return vector[row];
    }

    public void set(int row, float v) {
        vector[row] = v;
    }

    public void add(int row, float v) {
        vector[row] += v;
    }

    public void div(int row, float v) {
        vector[row] /= v;
    }

    public void clamp(int row, float min, float max) {
        vector[row] = Maths.clamp(vector[row], min, max);
    }

    @Override
    public String toString() {
        return "VectorNf{" +
                "vector=" + Arrays.toString(vector) +
                '}';
    }
}
