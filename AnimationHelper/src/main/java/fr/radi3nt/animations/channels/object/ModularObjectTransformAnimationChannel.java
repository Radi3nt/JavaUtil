package fr.radi3nt.animations.channels.object;

import fr.radi3nt.animations.channels.AnimationChannel;
import fr.radi3nt.maths.components.advanced.matrix.ArrayMatrix4x4;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ModularObjectTransformAnimationChannel extends AbstractTransformAnimationChannel implements ParentTransformAnimationChannel {

    private final Matrix4x4 current = ArrayMatrix4x4.newIdentity();
    private final Collection<TransformAnimationChannel> channels = new ArrayList<>();

    public ModularObjectTransformAnimationChannel() {

    }

    public ModularObjectTransformAnimationChannel(Collection<TransformAnimationChannel> channels) {
        this.channels.addAll(channels);
        setChildTransform();
    }

    public ModularObjectTransformAnimationChannel(TransformAnimationChannel... channels) {
        this.channels.addAll(Arrays.asList(channels));
        setChildTransform();
    }

    public void addChannel(TransformAnimationChannel channel) {
        channel.setParent(this);
        channels.add(channel);
    }

    public void addChannels(Collection<TransformAnimationChannel> channels) {
        this.channels.addAll(channels);
        for (TransformAnimationChannel channel : channels) {
            channel.setParent(this);
        }
    }

    @Override
    public void transformation(float time) {
        transform.identity();
        for (AnimationChannel channel : channels) {
            current.identity();
            channel.transformation(time);
            transform.multiply(current);
        }
        if (parent!=null)
            parent.getCurrentTransform().multiply(transform);
    }

    private void setChildTransform() {
        for (TransformAnimationChannel channel : channels) {
            channel.setParent(this);
        }
    }

    @Override
    public Matrix4x4 getCurrentTransform() {
        return current;
    }
}
