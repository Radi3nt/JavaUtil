package fr.radi3nt.armatures.armature.driver;

import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;

public interface BoneDriver {

    void set(Vector3f position);
    void set(Quaternion quaternion);
    void set(Vector3f translation, Quaternion rotation);
    void set(Vector3f translate, Quaternion rotate, Vector3f scale);

    Matrix4x4 getModelSpaceTransform(Matrix4x4 parentModelSpace);

}
