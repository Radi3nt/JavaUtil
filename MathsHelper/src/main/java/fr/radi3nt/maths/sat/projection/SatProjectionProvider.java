package fr.radi3nt.maths.sat.projection;

public interface SatProjectionProvider {

    SatProjection project(double min, double max);
    SatProjectionProvider duplicate();

}
