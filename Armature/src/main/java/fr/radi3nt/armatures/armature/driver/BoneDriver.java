package fr.radi3nt.armatures.armature.driver;

import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;

public interface BoneDriver {

    void setPosition(Vector3f position);
    void setScale(Vector3f scale);
    void setRotation(Quaternion quaternion);
    void addRotation(Quaternion quaternion);
    void setPositionAndRotation(Vector3f translation, Quaternion rotation);
    void setAll(Vector3f translate, Quaternion rotate, Vector3f scale);

    DriverResult getModelSpaceForParentTransform(DriverResult parent);

    Quaternion getLocalRotation();
    Vector3f getLocalTranslation();
    Vector3f getLocalSize();
}
