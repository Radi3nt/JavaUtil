package fr.radi3nt.noise.generator;

public interface DoubleNoise3D {

    double noise(double x, double y, double z);
    double noise(double x, double y, double z, double w);

}
