package fr.radi3nt.animations.channels.object.rotation;

import fr.radi3nt.animations.channels.object.AbstractTransformAnimationChannel;
import fr.radi3nt.animations.timeline.time.ChannelTimeline;
import fr.radi3nt.maths.components.advanced.matrix.angle.JavaMathAngle;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class RotationXEulerAnimationChannel extends AbstractTransformAnimationChannel {

    private final ChannelTimeline<Float> rotationX;

    public RotationXEulerAnimationChannel(ChannelTimeline<Float> rotationX) {
        this.rotationX = rotationX;
    }

    @Override
    public void transformation(float time) {
        transform.quaternionRotation(ComponentsQuaternion.fromAxisAndAngle(new SimpleVector3f(1, 0, 0), JavaMathAngle.fromDegree(rotationX.state(time))));
        super.transformation(time);
    }

    public ChannelTimeline<Float> getRotationX() {
        return rotationX;
    }
}