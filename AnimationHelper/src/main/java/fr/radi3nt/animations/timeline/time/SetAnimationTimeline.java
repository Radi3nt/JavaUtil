package fr.radi3nt.animations.timeline.time;

public class SetAnimationTimeline<T> implements ChannelTimeline<T> {

    private final T value;

    public SetAnimationTimeline(T value) {
        this.value = value;
    }

    @Override
    public T state(float time) {
        return value;
    }
}
