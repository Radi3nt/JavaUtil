package fr.radi3nt.animations.timeline;

import fr.radi3nt.animations.channels.ChannelIdentifier;
import fr.radi3nt.animations.timeline.blending.BlendingRepository;
import fr.radi3nt.animations.timeline.layers.AnimationLayer;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LayeredAnimationTimeline implements AnimationTimeline {

    private final Queue<AnimationLayer> animationLayers = new ConcurrentLinkedQueue<>();
    private final BlendingRepository blending;

    public LayeredAnimationTimeline(BlendingRepository blending) {
        this.blending = blending;
    }

    public Queue<AnimationLayer> getAnimationLayers() {
        return animationLayers;
    }

    @Override
    public void update(float delta) {
        for (AnimationLayer animationLayer : animationLayers) {
            animationLayer.getTimeline().update(delta);
        }
    }

    @Override
    public <T> T get(ChannelIdentifier channelIdentifier, T defaultResult) {
        T result = defaultResult;
        for (AnimationLayer animationLayer : animationLayers) {
            result = animationLayer.getLayerMode().blend(blending, animationLayer.getTimeline(), channelIdentifier, result);
        }
        return result;
    }

    @Override
    public Collection<String> getAvailableObjects() {
        Set<String> result = new HashSet<>();
        for (AnimationLayer animationLayer : animationLayers) {
            result.addAll(animationLayer.getTimeline().getAvailableObjects());
        }

        return result;
    }

}
