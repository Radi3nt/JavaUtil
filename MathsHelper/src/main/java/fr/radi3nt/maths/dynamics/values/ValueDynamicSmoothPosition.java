package fr.radi3nt.maths.dynamics.values;

import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;
import fr.radi3nt.maths.dynamics.SmoothTransform;

public class ValueDynamicSmoothPosition<P, T extends ValueDynamic<P>> {

    private final Vector3f targetPosition = new SimpleVector3f();

    private final Vector3f position = new SimpleVector3f();

    private final ValueDynamicArray<P, T> positionDynamic;

    public ValueDynamicSmoothPosition(DynamicValueFactory<P, T> factory, P linear) {
        this.positionDynamic = factory.createDynamicValue(linear, 3);
    }

    public Vector3f getTargetPosition() {
        return targetPosition;
    }

    public Vector3f getCurrentPosition() {
        return position;
    }

    public void setCurrentPosition(Vector3f position) {
        this.position.set(position);
        this.positionDynamic.get(0).setCurrentValue(position.getX());
        this.positionDynamic.get(1).setCurrentValue(position.getY());
        this.positionDynamic.get(2).setCurrentValue(position.getZ());
    }

    public void setTargetPosition(Vector3f position) {
        targetPosition.copy(position);
        this.positionDynamic.get(0).setTargetValue(targetPosition.getX());
        this.positionDynamic.get(1).setTargetValue(targetPosition.getY());
        this.positionDynamic.get(2).setTargetValue(targetPosition.getZ());
    }

    public void setLinearProperties(P linear) {
        this.positionDynamic.setProperties(linear);
    }


    public void update(float delta) {
        positionDynamic.update(delta);

        this.position.set(positionDynamic.get(0).getCurrentValue(),
                positionDynamic.get(1).getCurrentValue(),
                positionDynamic.get(2).getCurrentValue());
    }
}
