package fr.radi3nt.animations.timeline.time;

import fr.radi3nt.animations.timeline.state.AnimatedState;

import java.util.List;

public class TypedChannelTimeline<T> implements ChannelTimeline<T> {

    private final List<AnimatedState<T>> states;

    public TypedChannelTimeline(List<AnimatedState<T>> states) {
        this.states = states;
    }

    @Override
    public T state(float time) {
        if (time<0)
            return states.get(0).getStartState();

        TimeResult result = getAnimatedStateAtTime(time);

        if (timeIsOutOfAnimation(result))
            return states.get(states.size()-1).getEndState();

        AnimatedState<T> animatedState = result.animatedState;

        float timeStartingAtBit = time-result.currentTime;
        return animatedState.interpolate(timeStartingAtBit);
    }

    public float getTimelineLength() {
        float currentTime = 0;
        for (AnimatedState<T> state : states) {
            float stateTime = state.getTimeLength();
            currentTime+=stateTime;
        }
        return currentTime;
    }

    private boolean timeIsOutOfAnimation(TimeResult result) {
        return result==null;
    }

    private TimeResult getAnimatedStateAtTime(float time) {
        float currentTime = 0;
        for (AnimatedState<T> state : states) {
            float stateTime = state.getTimeLength();
            if (currentTime<=time && time<stateTime+currentTime)
                return new TimeResult(state, currentTime);
            currentTime+=stateTime;
        }
        return null;
    }


    private class TimeResult {

        private final AnimatedState<T> animatedState;
        private final float currentTime;

        private TimeResult(AnimatedState<T> animatedState, float currentTime) {
            this.animatedState = animatedState;
            this.currentTime = currentTime;
        }
    }
}
