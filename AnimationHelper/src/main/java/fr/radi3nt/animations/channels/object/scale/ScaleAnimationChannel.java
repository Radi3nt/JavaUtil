package fr.radi3nt.animations.channels.object.scale;

import fr.radi3nt.animations.channels.object.AbstractTransformAnimationChannel;
import fr.radi3nt.animations.timeline.time.ChannelTimeline;
import fr.radi3nt.maths.components.vectors.Vector3f;

public class ScaleAnimationChannel extends AbstractTransformAnimationChannel {

    private final ChannelTimeline<Vector3f> translationsTimeline;

    public ScaleAnimationChannel(ChannelTimeline<Vector3f> translationsTimeline) {
        this.translationsTimeline = translationsTimeline;
    }

    @Override
    public void transformation(float time) {
        transform.scale(translationsTimeline.state(time));
        super.transformation(time);
    }

}
