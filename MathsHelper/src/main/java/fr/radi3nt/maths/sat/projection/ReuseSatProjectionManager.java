package fr.radi3nt.maths.sat.projection;

public class ReuseSatProjectionManager implements SatProjectionProvider {

    private final SatProjection satProjection = new SatProjection(0, 0);

    @Override
    public SatProjection project(double min, double max) {
        satProjection.setMin(min);
        satProjection.setMax(max);
        return satProjection;
    }

    @Override
    public SatProjectionProvider duplicate() {
        return new ReuseSatProjectionManager();
    }
}
