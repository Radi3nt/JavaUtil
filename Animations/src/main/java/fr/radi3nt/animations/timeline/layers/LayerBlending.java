package fr.radi3nt.animations.timeline.layers;

import fr.radi3nt.animations.channels.ChannelIdentifier;
import fr.radi3nt.animations.timeline.AnimationTimeline;
import fr.radi3nt.animations.timeline.blending.BlendingRepository;

public interface LayerBlending {

    <T> T blend(BlendingRepository repository, AnimationTimeline timeline, ChannelIdentifier identifier, T defaultResult);

}
