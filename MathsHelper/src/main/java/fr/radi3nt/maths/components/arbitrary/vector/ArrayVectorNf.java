package fr.radi3nt.maths.components.arbitrary.vector;

import fr.radi3nt.maths.Maths;
import fr.radi3nt.maths.components.arbitrary.OperatingVectorNf;
import fr.radi3nt.maths.components.arbitrary.VectorNf;

import java.util.Arrays;

public class ArrayVectorNf implements VectorNf {

    private final float[] vector;

    public ArrayVectorNf(int size) {
        vector = new float[size];
    }

    private ArrayVectorNf(float[] vector) {
        this.vector = vector;
    }

    @Override
    public float length() {
        float total = 0;
        for (float v : vector) {
            total+=v*v;
        }
        return (float) Math.sqrt(total);
    }

    public int size() {
        return vector.length;
    }

    public float get(int row) {
        return vector[row];
    }

    @Override
    public ArrayVectorNf duplicate() {
        return new ArrayVectorNf(Arrays.copyOf(vector, vector.length));
    }

    @Override
    public ArrayVectorNf div(float number) {
        for (int i = 0; i < vector.length; i++) {
            vector[i]/=number;
        }
        return this;
    }

    @Override
    public ArrayVectorNf mul(float number) {
        for (int i = 0; i < vector.length; i++) {
            vector[i]*=number;
        }
        return this;
    }

    @Override
    public ArrayVectorNf add(OperatingVectorNf other) {
        if (other.size()!=this.size())
            throw new IllegalArgumentException();
        for (int i = 0; i < vector.length; i++) {
            vector[i]+=other.get(i);
        }
        return this;
    }

    @Override
    public ArrayVectorNf sub(OperatingVectorNf other) {
        if (other.size()!=this.size())
            throw new IllegalArgumentException();
        for (int i = 0; i < vector.length; i++) {
            vector[i]-=other.get(i);
        }
        return this;
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
