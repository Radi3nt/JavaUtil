package fr.radi3nt.armatures.armature.driver;

import fr.radi3nt.maths.components.advanced.matrix.ArrayMatrix4x4;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector4f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector4f;

public class ParentedBoneDriver implements BoneDriver {

    //If you choose to export with "Transform" category checked, set that to true
    private static final boolean TRANSFORM_MODE = false;

    private final Matrix4x4 resultTransform = ArrayMatrix4x4.newIdentity();
    private final Matrix4x4 resultParentTransform = ArrayMatrix4x4.newIdentity();
    private final Matrix4x4 localTransform = ArrayMatrix4x4.newIdentity();
    private final Matrix4x4 localRestTransform = ArrayMatrix4x4.newIdentity();

    private final Vector3f translation = new SimpleVector3f();
    private final Vector3f scale = new SimpleVector3f(1f, 1f, 1f);
    private final Quaternion rotation = ComponentsQuaternion.zero();

    private final BoneRestData restData;

    public ParentedBoneDriver(BoneRestData restData) {
        this.restData = restData;
        if (TRANSFORM_MODE) {
            this.translation.copy(restData.getPosition());
            this.rotation.copy(restData.getRotation());
        } else {
            this.localRestTransform.copy(restData.toMatrix());
        }
    }

    public BoneRestData getRestData() {
        return restData;
    }

    public void updateLocalRestTransform() {
        this.localRestTransform.copy(restData.toMatrix());
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
        Matrix4x4 parentTransform = parent.getCurrent();
        Vector3f applyingScale = scale.duplicate();

        localTransform.quaternionRotation(rotation);
        localTransform.translation(translation);
        localTransform.scalar(applyingScale);

        resultTransform.copy(parentTransform);
        resultTransform.multiply(ArrayMatrix4x4.transform(restData.getPosition()));
        parent.cancelScale(resultTransform);
        resultTransform.multiply(ArrayMatrix4x4.fromRotation(restData.getRotation()));
        resultTransform.multiply(ArrayMatrix4x4.fromScale(restData.getScale()));

        resultTransform.multiply(localTransform);
        return new ParentDriverResult(applyingScale);
    }

    @Override
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

    public Quaternion getModelRotation(Quaternion parent) {
        Quaternion result = ComponentsQuaternion.zero();
        result.multiply(parent);
        result.multiply(restData.getRotation());
        result.multiply(getLocalRotation());
        return result;
    }

    public float getBoneLength() {
        Vector4f bone = new SimpleVector4f(0, 0, 0, 1);
        localRestTransform.transform(bone);
        return bone.length();
    }

    public class ParentDriverResult implements DriverResult {

        private final Vector3f applyingScale;

        public ParentDriverResult(Vector3f applyingScale) {
            this.applyingScale = applyingScale;
        }

        @Override
        public Matrix4x4 getCurrent() {
            return resultTransform;
        }

        @Override
        public void cancelScale(Matrix4x4 current) {
            current.multiply(ArrayMatrix4x4.fromScale(new SimpleVector3f(1f, 1f, 1f).div(applyingScale)));
        }
    }
}
