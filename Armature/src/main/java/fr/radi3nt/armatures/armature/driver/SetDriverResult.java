package fr.radi3nt.armatures.armature.driver;

import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;

public class SetDriverResult implements DriverResult {

    private final Matrix4x4 result;

    public SetDriverResult(Matrix4x4 result) {
        this.result = result;
    }

    @Override
    public Matrix4x4 getCurrent() {
        return result;
    }

    @Override
    public void cancelScale(Matrix4x4 current) {

    }
}
