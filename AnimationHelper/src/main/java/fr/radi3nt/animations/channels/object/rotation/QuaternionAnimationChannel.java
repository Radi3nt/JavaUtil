package fr.radi3nt.animations.channels.object.rotation;

import fr.radi3nt.animations.channels.object.AbstractTransformAnimationChannel;
import fr.radi3nt.animations.timeline.time.ChannelTimeline;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;

public class QuaternionAnimationChannel extends AbstractTransformAnimationChannel {

    private final ChannelTimeline<Quaternion> quaternionTimeline;

    public QuaternionAnimationChannel(ChannelTimeline<Quaternion> quaternionTimeline) {
        this.quaternionTimeline = quaternionTimeline;
    }

    @Override
    public void transformation(float time) {
        transform.quaternionRotation(quaternionTimeline.state(time));
        super.transformation(time);
    }

    public ChannelTimeline<Quaternion> getQuaternionTimeline() {
        return quaternionTimeline;
    }
}
