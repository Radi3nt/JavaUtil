package fr.radi3nt.animations.channels.object;

import fr.radi3nt.maths.components.advanced.matrix.ArrayMatrix4x4;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;

public abstract class AbstractTransformAnimationChannel implements TransformAnimationChannel {

    protected final Matrix4x4 transform = ArrayMatrix4x4.newIdentity();
    protected ParentTransformAnimationChannel parent;

    @Override
    public void setParent(ParentTransformAnimationChannel transform) {
        parent = transform;
    }

    @Override
    public void transformation(float time) {
        applyLocalToParent();
    }

    private void applyLocalToParent() {
        if (parent!=null) {
            parent.getCurrentTransform().multiply(transform);
        }
    }

    @Override
    public Matrix4x4 getLocalTransform() {
        return transform;
    }
}
