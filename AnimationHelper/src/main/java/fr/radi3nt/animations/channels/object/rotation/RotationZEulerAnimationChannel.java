package fr.radi3nt.animations.channels.object.rotation;

import fr.radi3nt.animations.channels.object.AbstractTransformAnimationChannel;
import fr.radi3nt.animations.timeline.time.ChannelTimeline;
import fr.radi3nt.maths.components.advanced.matrix.angle.JavaMathAngle;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class RotationZEulerAnimationChannel extends AbstractTransformAnimationChannel {

    private final ChannelTimeline<Float> rotationZ;

    public RotationZEulerAnimationChannel(ChannelTimeline<Float> rotationZ) {
        this.rotationZ = rotationZ;
    }

    @Override
    public void transformation(float time) {
        transform.quaternionRotation(ComponentsQuaternion.fromAxisAndAngle(new SimpleVector3f(0, 0, 1), JavaMathAngle.fromDegree(rotationZ.state(time))));
        super.transformation(time);
    }

    public ChannelTimeline<Float> getRotationZ() {
        return rotationZ;
    }
}