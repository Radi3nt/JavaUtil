package fr.radi3nt.animations.channels.object.translation;

import fr.radi3nt.animations.channels.object.AbstractTransformAnimationChannel;
import fr.radi3nt.animations.channels.object.TransformAnimationChannel;
import fr.radi3nt.animations.timeline.time.ChannelTimeline;
import fr.radi3nt.maths.components.advanced.matrix.ArrayMatrix4x4;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;
import fr.radi3nt.maths.components.vectors.Vector3f;

public class TranslationAnimationChannel extends AbstractTransformAnimationChannel {

    private final ChannelTimeline<Vector3f> translationsTimeline;
    private final Matrix4x4 transform = ArrayMatrix4x4.newIdentity();
    private TransformAnimationChannel parent;

    public TranslationAnimationChannel(ChannelTimeline<Vector3f> translationsTimeline) {
        this.translationsTimeline = translationsTimeline;
    }

    @Override
    public void transformation(float time) {
        transform.translation(translationsTimeline.state(time));
        super.transformation(time);
    }
}
