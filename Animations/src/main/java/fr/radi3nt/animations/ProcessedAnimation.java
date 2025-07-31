package fr.radi3nt.animations;

import fr.radi3nt.animations.channels.ChannelIdentifier;
import fr.radi3nt.animations.channels.KeyframeChannel;

import java.util.Set;
import java.util.function.Supplier;

public class ProcessedAnimation implements AnimatedPropertyProvider {

    public final AnimationClip animationClip;
    private final boolean looping;
    private float relativeTime;

    public ProcessedAnimation(AnimationClip animationClip, boolean looping) {
        this.animationClip = animationClip;
        this.looping = looping;
    }

    public ProcessedAnimation(AnimationClip animationClip, boolean looping, float relativeTime) {
        this.animationClip = animationClip;
        this.looping = looping;
        this.relativeTime = relativeTime;
    }

    public float getRelativeTime() {
        return relativeTime;
    }

    public boolean isDone() {
        return relativeTime>animationClip.duration;
    }

    @Override
    public void incrementTime(float delta) {
        relativeTime+=delta;
        if (isDone() && looping)
            relativeTime-=animationClip.duration;
    }

    @Override
    public <A> Supplier<A> get(ChannelIdentifier identifier) {
        return () -> {
            KeyframeChannel<?> keyframeChannel = animationClip.channels.get(identifier);
            if (keyframeChannel==null)
                return null;
            return (A) keyframeChannel.transform(relativeTime);
        };
    }

    @Override
    public void addConcernedObjects(Set<String> objects) {
        for (ChannelIdentifier channelIdentifier : animationClip.channels.keySet()) {
            objects.add(channelIdentifier.objectName);
        }
    }

}
