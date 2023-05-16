package fr.radi3nt.maths.components.arbitrary;

import fr.radi3nt.maths.components.vectors.Vector;

public interface VectorNd extends Vector {

    double get(int row);

    void set(int row, double v);

    void add(int row, double v);

    void div(int row, double v);

    void clamp(int row, double min, double max);

    int size();

    void scalar(VectorNd kd);
}
