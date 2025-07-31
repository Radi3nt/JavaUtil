package fr.radi3nt.animations.timeline;

import fr.radi3nt.animations.channels.ChannelIdentifier;

import java.util.Collection;

public interface AnimationTimeline {

    void update(float delta);
    <T> T get(ChannelIdentifier channelIdentifier, T defaultResult);
    Collection<String> getAvailableObjects();

}
