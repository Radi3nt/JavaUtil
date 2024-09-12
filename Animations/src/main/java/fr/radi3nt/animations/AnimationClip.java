package fr.radi3nt.animations;

import fr.radi3nt.animations.channels.ChannelIdentifier;
import fr.radi3nt.animations.channels.KeyframeChannel;

import java.util.Map;

public class AnimationClip {

    public final float duration;
    public final Map<ChannelIdentifier, ? extends KeyframeChannel<?>> channels;

    public AnimationClip(float duration, Map<ChannelIdentifier, ? extends KeyframeChannel<?>> channels) {
        this.duration = duration;
        this.channels = channels;
    }


}
