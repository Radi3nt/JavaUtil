package fr.radi3nt.animations;

import fr.radi3nt.animations.channels.ChannelIdentifier;

import java.util.Set;
import java.util.function.Supplier;

public interface AnimatedPropertyProvider {

    void incrementTime(float delta);
    <A> Supplier<A> get(ChannelIdentifier identifier);

    void addConcernedObjects(Set<String> objects);
}
