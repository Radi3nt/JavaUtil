package fr.radi3nt.armatures.armature.driver;

import fr.radi3nt.maths.components.advanced.matrix.ArrayMatrix4x4;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class WorldIndependentBoneDriver implements BoneDriver {

    private final Matrix4x4 resultTransform = ArrayMatrix4x4.newIdentity();
    private final Matrix4x4 localTransform = ArrayMatrix4x4.newIdentity();

    private final Vector3f translation = new SimpleVector3f();
    private final Vector3f scale = new SimpleVector3f(1f, 1f, 1f);
    private final Quaternion rotation = ComponentsQuaternion.zero();

    public WorldIndependentBoneDriver() {

    }

    @Override
    public void setPosition(Vector3f position) {
        this.translation.copy(position);
    }

    @Override
    public void setScale(Vector3f scale) {
        this.scale.copy(scale);
    }

    @Override
    public void setRotation(Quaternion quaternion) {
        this.rotation.copy(quaternion);
    }

    @Override
    public void addRotation(Quaternion quaternion) {
        rotation.multiply(quaternion);
    }

    @Override
    public void setPositionAndRotation(Vector3f translation, Quaternion rotation) {
        setPosition(translation);
        setRotation(rotation);
    }

    @Override
    public void setAll(Vector3f translate, Quaternion rotate, Vector3f scale) {
        setPositionAndRotation(translate, rotate);
        setScale(scale);
    }

    @Override
    public DriverResult getModelSpaceForParentTransform(DriverResult parent) {
        localTransform.quaternionRotation(getLocalRotation());
        localTransform.translation(getTranslation());
        localTransform.scalar(getScale());

        resultTransform.copy(localTransform);
        return new SetDriverResult(resultTransform);
    }

    public Quaternion getLocalRotation() {
        return rotation;
    }

    @Override
    public Vector3f getLocalTranslation() {
        return translation;
    }

    @Override
    public Vector3f getLocalSize() {
        return scale;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public Vector3f getScale() {
        return scale;
    }
}
