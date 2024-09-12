package fr.radi3nt.armatures.armature.driver;

import fr.radi3nt.maths.components.advanced.matrix.ArrayMatrix4x4;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class WorldRestedBoneDriver implements BoneDriver {

    private final Matrix4x4 resultTransform = ArrayMatrix4x4.newIdentity();
    private final Matrix4x4 localTransform = ArrayMatrix4x4.newIdentity();
    private final Matrix4x4 localRestTransform = ArrayMatrix4x4.newIdentity();

    private final Vector3f translation = new SimpleVector3f();
    private final Quaternion rotation = ComponentsQuaternion.zero();

    public WorldRestedBoneDriver(BoneRestData restData) {
        this.localRestTransform.copy(restData.toMatrix());
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

    @Override
    public Matrix4x4 getModelSpaceTransform(Matrix4x4 parentModelSpace) {
        localTransform.quaternionRotation(rotation);
        localTransform.translation(translation);

        resultTransform.copy(localRestTransform);
        resultTransform.multiply(localTransform);

        return resultTransform;
    }
}
