package fr.radi3nt.maths.components.arbitrary;

import fr.radi3nt.maths.components.vectors.Vector;

public interface VectorNf extends Vector {

    float get(int row);

    void set(int row, float v);

    void add(int row, float v);

    void div(int row, float v);

    void clamp(int row, float min, float max);

    int size();

}
