package fr.radi3nt.armatures.armature.driver;

import fr.radi3nt.maths.components.advanced.matrix.ArrayMatrix4x4;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class ScaleDriverResult implements DriverResult {

    private final Matrix4x4 result;
    private final Vector3f scale;

    public ScaleDriverResult(Matrix4x4 result, Vector3f scale) {
        this.result = result;
        this.scale = scale;
    }

    @Override
    public Matrix4x4 getCurrent() {
        return result;
    }

    @Override
    public void cancelScale(Matrix4x4 current) {
        current.multiply(ArrayMatrix4x4.fromScale(new SimpleVector3f(1f, 1f, 1f).div(scale)));
    }
}
