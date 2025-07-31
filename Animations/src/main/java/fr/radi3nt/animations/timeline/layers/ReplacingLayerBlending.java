package fr.radi3nt.animations.timeline.layers;

import fr.radi3nt.animations.channels.ChannelIdentifier;
import fr.radi3nt.animations.timeline.AnimationTimeline;
import fr.radi3nt.animations.timeline.blending.BlendingRepository;

public class ReplacingLayerBlending implements LayerBlending {
    @Override
    public <T> T blend(BlendingRepository repository, AnimationTimeline timeline, ChannelIdentifier identifier, T previousResult) {
        return timeline.get(identifier, previousResult);
    }
}
