package fr.radi3nt.animations.channels;

import fr.radi3nt.animations.Animation;

import java.util.ArrayList;
import java.util.Arrays;

public class ChanneledAnimation implements Animation {

    private final Iterable<AnimationChannel> channels;
    private final float animationTime;

    public ChanneledAnimation(Iterable<AnimationChannel> channels, float animationTime) {
        this.channels = channels;
        this.animationTime = animationTime;
    }

    public ChanneledAnimation(float animationTime, AnimationChannel... channels) {
        this(new ArrayList<>(Arrays.asList(channels)), animationTime);
    }

    @Override
    public void transform(float time) {
        for (AnimationChannel channel : channels) {
            channel.transformation(time);
        }
    }

    @Override
    public float getAnimationTime() {
        return animationTime;
    }
}
