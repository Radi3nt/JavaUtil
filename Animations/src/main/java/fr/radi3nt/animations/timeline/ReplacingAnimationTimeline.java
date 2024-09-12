package fr.radi3nt.animations.timeline;

import fr.radi3nt.animations.AnimationClip;
import fr.radi3nt.animations.ProcessedAnimation;
import fr.radi3nt.animations.channels.ChannelIdentifier;
import fr.radi3nt.animations.channels.KeyframeChannel;

import java.util.*;

public class ReplacingAnimationTimeline implements AnimationTimeline {

    private final List<ProcessedAnimation> processedAnimations = new ArrayList<>();

    public void addAnimation(AnimationClip animationClip, boolean looping) {
        processedAnimations.add(new ProcessedAnimation(animationClip, looping));
    }

    @Override
    public void update(float delta) {
        for (ProcessedAnimation processedAnimation : processedAnimations) {
            processedAnimation.incrementTime(delta);
        }
    }

    @Override
    public <T> T getAnimatedPropertyOrDefault(ChannelIdentifier identifier, T defaultResult) {
        KeyframeChannel<?> lastChannel = null;
        float t = 0;

        for (ProcessedAnimation processedAnimation : processedAnimations) {
            lastChannel = processedAnimation.animationClip.channels.get(identifier);
            t = processedAnimation.getRelativeTime();
        }

        return lastChannel==null ? defaultResult : (T) lastChannel.transform(t);
    }

    @Override
    public Collection<String> getAvailableObjects() {
        Set<String> objects = new HashSet<>();
        for (ProcessedAnimation processedAnimation : processedAnimations) {
            for (ChannelIdentifier channelIdentifier : processedAnimation.animationClip.channels.keySet()) {
                objects.add(channelIdentifier.objectName);
            }
        }

        return objects;
    }
}
