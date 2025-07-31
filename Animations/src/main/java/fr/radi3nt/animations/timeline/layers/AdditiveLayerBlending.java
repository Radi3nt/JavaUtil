package fr.radi3nt.animations.timeline.layers;

import fr.radi3nt.animations.channels.ChannelIdentifier;
import fr.radi3nt.animations.timeline.AnimationTimeline;
import fr.radi3nt.animations.timeline.blending.BlendingRepository;

public class AdditiveLayerBlending implements LayerBlending {

    private final float weight;

    public AdditiveLayerBlending(float weight) {
        this.weight = weight;
    }

    @Override
    public <T> T blend(BlendingRepository repository, AnimationTimeline timeline, ChannelIdentifier identifier, T defaultResult) {
        if (weight==0)
            return defaultResult;
        T second = timeline.get(identifier, null);
        if (second==null)
            return defaultResult;
        return repository.additive(defaultResult, second, weight);
    }
}
