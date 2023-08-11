package fr.radi3nt.animations.channels.object.translation;

import fr.radi3nt.animations.channels.object.AbstractTransformAnimationChannel;
import fr.radi3nt.animations.timeline.time.ChannelTimeline;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class TranslationSeparatedAnimationChannel extends AbstractTransformAnimationChannel {

    private final ChannelTimeline<Float> xTimeline;
    private final ChannelTimeline<Float> yTimeline;
    private final ChannelTimeline<Float> zTimeline;

    public TranslationSeparatedAnimationChannel(ChannelTimeline<Float> xTimeline, ChannelTimeline<Float> yTimeline, ChannelTimeline<Float> zTimeline) {
        this.xTimeline = xTimeline;
        this.yTimeline = yTimeline;
        this.zTimeline = zTimeline;
    }

    @Override
    public void transformation(float time) {
        Vector3f translation = new SimpleVector3f(xTimeline.state(time), yTimeline.state(time), zTimeline.state(time));
        transform.translation(translation);

        super.transformation(time);
    }

}
