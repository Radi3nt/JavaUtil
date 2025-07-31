package fr.radi3nt.armatures.armature.driver;

import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;

public class EncapsulatingDriverResult implements DriverResult {

    private final Matrix4x4 current;
    private final DriverResult cancelScale;

    public EncapsulatingDriverResult(Matrix4x4 current, DriverResult cancelScale) {
        this.current = current;
        this.cancelScale = cancelScale;
    }

    @Override
    public Matrix4x4 getCurrent() {
        return current;
    }

    @Override
    public void cancelScale(Matrix4x4 current) {
        cancelScale.cancelScale(current);
    }
}
