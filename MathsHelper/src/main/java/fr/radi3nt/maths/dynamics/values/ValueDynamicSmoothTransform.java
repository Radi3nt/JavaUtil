package fr.radi3nt.maths.dynamics.values;

import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;
import fr.radi3nt.maths.dynamics.SmoothTransform;

public class ValueDynamicSmoothTransform<P, T extends ValueDynamic<P>> implements SmoothTransform<P> {

    private final Vector3f targetPosition = new SimpleVector3f();
    private final Quaternion targetOrientation = ComponentsQuaternion.zero();

    private final Vector3f position = new SimpleVector3f();
    private final Quaternion orientation = ComponentsQuaternion.zero();

    private final ValueDynamicArray<P, T> positionDynamic;
    private final ValueDynamicArray<P, T> rotationDynamic;

    public ValueDynamicSmoothTransform(DynamicValueFactory<P, T> factory, P linear, P angular) {
        this.positionDynamic = factory.createDynamicValue(linear, 3);
        this.rotationDynamic = factory.createDynamicValue(angular, 4);
    }

    @Override
    public Vector3f getTargetPosition() {
        return targetPosition;
    }

    @Override
    public Quaternion getTargetOrientation() {
        return targetOrientation;
    }

    @Override
    public Vector3f getCurrentPosition() {
        return position;
    }

    @Override
    public Quaternion getCurrentOrientation() {
        return orientation;
    }

    @Override
    public void setCurrentPosition(Vector3f position) {
        this.position.set(position);
        this.positionDynamic.get(0).setCurrentValue(position.getX());
        this.positionDynamic.get(1).setCurrentValue(position.getY());
        this.positionDynamic.get(2).setCurrentValue(position.getZ());
    }

    @Override
    public void setCurrentOrientation(Quaternion orientation) {
        this.orientation.copy(orientation);
    }

    @Override
    public void setTargetPosition(Vector3f position) {
        targetPosition.copy(position);
        this.positionDynamic.get(0).setTargetValue(targetPosition.getX());
        this.positionDynamic.get(1).setTargetValue(targetPosition.getY());
        this.positionDynamic.get(2).setTargetValue(targetPosition.getZ());
    }

    @Override
    public void setTargetOrientation(Quaternion orientation) {
        targetOrientation.copy(orientation);
    }

    @Override
    public void setLinearProperties(P linear) {
        this.positionDynamic.setProperties(linear);
    }

    @Override
    public void setAngularProperties(P angular) {
        this.rotationDynamic.setProperties(angular);
    }

    @Override
    public void update(float delta) {
        Quaternion currentTarget = targetOrientation.duplicate();

        if (orientation.dot(currentTarget) <= 0) {
            orientation.multiply(-1f);
        }
        this.rotationDynamic.get(0).setCurrentValue(orientation.getX());
        this.rotationDynamic.get(1).setCurrentValue(orientation.getY());
        this.rotationDynamic.get(2).setCurrentValue(orientation.getZ());
        this.rotationDynamic.get(3).setCurrentValue(orientation.getW());

        this.rotationDynamic.get(0).setTargetValue(currentTarget.getX());
        this.rotationDynamic.get(1).setTargetValue(currentTarget.getY());
        this.rotationDynamic.get(2).setTargetValue(currentTarget.getZ());
        this.rotationDynamic.get(3).setTargetValue(currentTarget.getW());

        positionDynamic.update(delta);
        rotationDynamic.update(delta);

        this.position.set(positionDynamic.get(0).getCurrentValue(),
                positionDynamic.get(1).getCurrentValue(),
                positionDynamic.get(2).getCurrentValue());

        this.orientation.set(
                rotationDynamic.get(0).getCurrentValue(),
                rotationDynamic.get(1).getCurrentValue(),
                rotationDynamic.get(2).getCurrentValue(),
                rotationDynamic.get(3).getCurrentValue());
        this.orientation.normaliseSafely();
    }
}
