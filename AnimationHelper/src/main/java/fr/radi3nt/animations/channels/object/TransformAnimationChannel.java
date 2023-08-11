package fr.radi3nt.animations.channels.object;

import fr.radi3nt.animations.channels.AnimationChannel;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;

public interface TransformAnimationChannel extends AnimationChannel {

    void setParent(ParentTransformAnimationChannel transform);
    Matrix4x4 getLocalTransform();

}
