package fr.radi3nt.maths.sat.projection;

import fr.radi3nt.maths.components.Vector3D;

public interface SatProjectionProvider {

    SatProjection project(Vector3D minVertex, Vector3D maxVertex, double min, double max);
    SatProjectionProvider duplicate();

}
