package fr.radi3nt.maths.dynamics.values;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class ValueDynamicSmoothDirection<P, T extends ValueDynamic<P>> {

    private final Vector3f targetDirection = new SimpleVector3f();

    private final Vector3f direction = new SimpleVector3f();

    private final ValueDynamicArray<P, T> positionDynamic;

    public ValueDynamicSmoothDirection(DynamicValueFactory<P, T> factory, P linear) {
        this.positionDynamic = factory.createDynamicValue(linear, 3);
    }

    public Vector3f getTargetDirection() {
        return targetDirection;
    }

    public Vector3f getCurrentDirection() {
        return direction;
    }

    public void setCurrentDirection(Vector3f direction) {
        this.direction.set(direction);
        this.positionDynamic.get(0).setCurrentValue(direction.getX());
        this.positionDynamic.get(1).setCurrentValue(direction.getY());
        this.positionDynamic.get(2).setCurrentValue(direction.getZ());
    }

    public void setTargetDirection(Vector3f direction) {
        targetDirection.copy(direction);
        this.positionDynamic.get(0).setTargetValue(targetDirection.getX());
        this.positionDynamic.get(1).setTargetValue(targetDirection.getY());
        this.positionDynamic.get(2).setTargetValue(targetDirection.getZ());
    }

    public void setLinearProperties(P linear) {
        this.positionDynamic.setProperties(linear);
    }


    public void update(float delta) {
        positionDynamic.update(delta);

        this.direction.set(positionDynamic.get(0).getCurrentValue(),
                positionDynamic.get(1).getCurrentValue(),
                positionDynamic.get(2).getCurrentValue());
        this.direction.normalizeSafely();
        this.positionDynamic.get(0).setCurrentValue(direction.getX());
        this.positionDynamic.get(1).setCurrentValue(direction.getY());
        this.positionDynamic.get(2).setCurrentValue(direction.getZ());
    }
}
