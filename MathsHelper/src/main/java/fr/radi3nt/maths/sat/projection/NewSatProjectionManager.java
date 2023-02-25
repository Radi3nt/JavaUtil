package fr.radi3nt.maths.sat.projection;

public class NewSatProjectionManager implements SatProjectionProvider {
    @Override
    public SatProjection project(double min, double max) {
        return new SatProjection(min, max);
    }

    @Override
    public SatProjectionProvider duplicate() {
        return new NewSatProjectionManager();
    }
}
