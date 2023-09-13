package fr.radi3nt.maths.components.arbitrary.vector;

import fr.radi3nt.maths.Maths;
import fr.radi3nt.maths.components.arbitrary.OperatingVectorNf;
import fr.radi3nt.maths.components.arbitrary.VectorNf;

import java.util.ArrayList;
import java.util.List;

public class ExtensibleVectorNf implements VectorNf {

    private final List<Float> vector;

    public ExtensibleVectorNf() {
        vector = new ArrayList<>();
    }

    private ExtensibleVectorNf(List<Float> vector) {
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
        return vector.size();
    }

    public float get(int row) {
        return vector.get(row);
    }

    @Override
    public ExtensibleVectorNf duplicate() {
        return new ExtensibleVectorNf(new ArrayList<>(vector));
    }

    @Override
    public OperatingVectorNf div(float number) {
        vector.replaceAll(aFloat -> aFloat/number);
        return this;
    }

    @Override
    public OperatingVectorNf mul(float number) {
        vector.replaceAll(aFloat -> aFloat*number);
        return this;
    }

    @Override
    public OperatingVectorNf add(OperatingVectorNf other) {
        if (other.size()!=this.size())
            throw new IllegalArgumentException();
        for (int i = 0; i < vector.size(); i++) {
            vector.set(i, vector.get(i)+other.get(i));
        }
        return this;
    }

    @Override
    public OperatingVectorNf sub(OperatingVectorNf other) {
        if (other.size()!=this.size())
            throw new IllegalArgumentException();
        for (int i = 0; i < vector.size(); i++) {
            vector.set(i, vector.get(i)-other.get(i));
        }
        return this;
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
