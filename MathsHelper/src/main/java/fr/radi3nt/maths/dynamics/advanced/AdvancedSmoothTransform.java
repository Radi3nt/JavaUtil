package fr.radi3nt.maths.dynamics.advanced;

import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector4f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector4f;
import fr.radi3nt.maths.dynamics.SmoothTransform;

public class AdvancedSmoothTransform implements SmoothTransform<DynamicsConstants> {

    protected final VectorSmoothDynamics<Vector3f> positionDynamics;
    protected final VectorRotationSmoothDynamics rotationDynamics;

    public final Vector3f targetPosition = new SimpleVector3f();
    public final Quaternion targetRotation = ComponentsQuaternion.zero();

    private final Vector3f position = new SimpleVector3f();
    private final Quaternion rotation = ComponentsQuaternion.zero();

    public AdvancedSmoothTransform(DynamicsConstants constants) {
        this.positionDynamics = new VectorSmoothDynamics<>(constants, new SimpleVector3f());
        this.rotationDynamics = new VectorRotationSmoothDynamics(constants, new SimpleVector4f(0, 0, 0, 1), true);
    }

    public AdvancedSmoothTransform(DynamicsConstants position, DynamicsConstants rotation) {
        this.positionDynamics = new VectorSmoothDynamics<>(position, new SimpleVector3f());
        this.rotationDynamics = new VectorRotationSmoothDynamics(rotation, new SimpleVector4f(0, 0, 0, 1), true);
    }

    public AdvancedSmoothTransform(Vector3f position, Quaternion rotation, DynamicsConstants positionConstants, DynamicsConstants rotationConstants) {
        targetPosition.copy(position);
        targetRotation.copy(rotation);

        this.position.copy(position);
        this.rotation.copy(rotation);

        this.positionDynamics = new VectorSmoothDynamics<>(positionConstants, targetPosition);
        this.rotationDynamics = new VectorRotationSmoothDynamics(rotationConstants, new SimpleVector4f(targetRotation), true);
    }

    public void reset(Vector3f pos, Quaternion rotation) {
        this.targetPosition.copy(pos);
        this.position.copy(pos);

        this.rotation.copy(rotation);
        this.targetRotation.copy(rotation);

        this.positionDynamics.reset(targetPosition);
        Vector4f currentRot = new SimpleVector4f(rotation);
        this.rotationDynamics.reset(currentRot);
    }

    @Override
    public Vector3f getTargetPosition() {
        return targetPosition;
    }

    @Override
    public Quaternion getTargetOrientation() {
        return targetRotation;
    }

    @Override
    public Vector3f getCurrentPosition() {
        return position;
    }

    @Override
    public Quaternion getCurrentOrientation() {
        return rotation;
    }

    @Override
    public void setCurrentPosition(Vector3f position) {
        this.positionDynamics.setCurrent(position);
    }

    @Override
    public void setCurrentOrientation(Quaternion orientation) {
        this.rotationDynamics.setCurrent(new SimpleVector4f(orientation));
    }

    @Override
    public void setTargetPosition(Vector3f position) {
        this.targetPosition.copy(position);
    }

    @Override
    public void setTargetOrientation(Quaternion orientation) {
        this.targetRotation.copy(orientation);
    }

    @Override
    public void setLinearProperties(DynamicsConstants linear) {
        positionDynamics.setConstants(linear);
    }

    @Override
    public void setAngularProperties(DynamicsConstants angular) {
        rotationDynamics.setConstants(angular);
    }

    public void update(float delta) {
        positionDynamics.setInputCurrent(targetPosition);
        rotationDynamics.setInputCurrent(new SimpleVector4f(targetRotation));

        positionDynamics.update(delta);
        rotationDynamics.update(delta);

        this.position.copy(positionDynamics.getResponse());
        this.rotation.copy(rotationDynamics.getResponse());
    }

    public Vector3f getLinearVelocity() {
        return positionDynamics.getResponseDerivative();
    }

    public Vector3f getPosition() {
        return position;
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public void setConstants(DynamicsConstants constants) {
        positionDynamics.setConstants(constants);
        rotationDynamics.setConstants(constants);
    }

    public void setPositionConstants(DynamicsConstants constants) {
        positionDynamics.setConstants(constants);
    }

    public void setRotationConstants(DynamicsConstants constants) {
        rotationDynamics.setConstants(constants);
    }

}
