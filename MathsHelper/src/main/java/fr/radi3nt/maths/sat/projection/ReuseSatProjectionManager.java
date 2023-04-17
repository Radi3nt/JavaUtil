package fr.radi3nt.maths.sat.projection;

import fr.radi3nt.maths.components.Vector3D;

public class ReuseSatProjectionManager implements SatProjectionProvider {

    private final SatProjection satProjection = new SatProjection(0, 0);

    @Override
    public SatProjection project(Vector3D minVertex, Vector3D maxVertex, double min, double max) {
        satProjection.setMin(minVertex, min);
        satProjection.setMax(maxVertex, max);
        return satProjection;
    }

    @Override
    public SatProjectionProvider duplicate() {
        return new ReuseSatProjectionManager();
    }
}
