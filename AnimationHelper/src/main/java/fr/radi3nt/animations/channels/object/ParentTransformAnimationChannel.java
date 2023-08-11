package fr.radi3nt.animations.channels.object;

import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;

public interface ParentTransformAnimationChannel extends TransformAnimationChannel {

    Matrix4x4 getCurrentTransform();

}
