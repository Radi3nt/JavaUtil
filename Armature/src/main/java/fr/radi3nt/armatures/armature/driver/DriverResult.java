package fr.radi3nt.armatures.armature.driver;

import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;
import fr.radi3nt.maths.components.vectors.Vector3f;

public interface DriverResult {

    Matrix4x4 getCurrent();
    void cancelScale(Matrix4x4 current);

}
