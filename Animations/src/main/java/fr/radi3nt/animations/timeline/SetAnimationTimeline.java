package fr.radi3nt.animations.timeline;

import fr.radi3nt.animations.AnimationClip;
import fr.radi3nt.animations.AnimatedPropertyProvider;
import fr.radi3nt.animations.ProcessedAnimation;
import fr.radi3nt.animations.channels.ChannelIdentifier;

import java.util.*;
import java.util.function.Supplier;

public class SetAnimationTimeline implements AnimationTimeline {

    private final List<AnimatedPropertyProvider> processedAnimations = new ArrayList<>();

    public void addAnimation(AnimationClip animationClip, boolean looping) {
        processedAnimations.add(new ProcessedAnimation(animationClip, looping));
    }

    public void addAnimation(AnimatedPropertyProvider provider) {
        processedAnimations.add(provider);
    }

    public void removeAnimation(AnimatedPropertyProvider provider) {
        processedAnimations.remove(provider);
    }

    @Override
    public void update(float delta) {
        for (AnimatedPropertyProvider processedAnimation : processedAnimations) {
            processedAnimation.incrementTime(delta);
        }
    }

    @Override
    public <T> T get(ChannelIdentifier identifier, T defaultResult) {
        Supplier<?> lastChannel = null;

        for (AnimatedPropertyProvider processedAnimation : processedAnimations) {
            lastChannel = processedAnimation.get(identifier);
        }

        return lastChannel==null ? defaultResult : (T) lastChannel.get();
    }

    @Override
    public Collection<String> getAvailableObjects() {
        Set<String> objects = new HashSet<>();
        for (AnimatedPropertyProvider processedAnimation : processedAnimations) {
            processedAnimation.addConcernedObjects(objects);
        }

        return objects;
    }
}
