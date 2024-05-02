package fr.radi3nt.maths.components.arbitrary;

import fr.radi3nt.maths.components.vectors.Vector;

public interface VectorNf extends Vector, OperatingVectorNf {

    float get(int row);

    void set(int row, float v);

    void add(int row, float v);

    void div(int row, float v);

    void clamp(int row, float min, float max);

    int size();

    VectorNf duplicate();

    default void copy(OperatingVectorNf other) {
        if (other.size()!=this.size()) {
            throw new IllegalArgumentException("Other vector doesn't have the same size");
        }
        for (int i = 0; i < this.size(); i++) {
            this.set(i, other.get(i));
        }
    }
}
