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
    private final Quaternion rotation = ComponentsQuaternion.zero();

    public WorldIndependentBoneDriver() {

    }

    @Override
    public void set(Vector3f position) {
        this.translation.copy(position);
    }

    @Override
    public void set(Quaternion quaternion) {
        this.rotation.copy(quaternion);
    }

    @Override
    public void set(Vector3f translation, Quaternion rotation) {
        set(translation);
        set(rotation);
    }

    @Override
    public void set(Vector3f translate, Quaternion rotate, Vector3f scale) {
        set(translate, rotate);
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    @Override
    public Matrix4x4 getModelSpaceTransform(Matrix4x4 parentModelSpace) {
        localTransform.quaternionRotation(getRotation());
        localTransform.translation(getTranslation());

        resultTransform.copy(localTransform);

        return resultTransform;
    }
}
