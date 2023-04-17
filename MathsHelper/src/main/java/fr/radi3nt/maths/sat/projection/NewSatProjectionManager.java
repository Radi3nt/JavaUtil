package fr.radi3nt.maths.sat.projection;

import fr.radi3nt.maths.components.Vector3D;

public class NewSatProjectionManager implements SatProjectionProvider {
    @Override
    public SatProjection project(Vector3D minVertex, Vector3D maxVertex, double min, double max) {
        return new SatProjection(min, max);
    }

    @Override
    public SatProjectionProvider duplicate() {
        return new NewSatProjectionManager();
    }
}
