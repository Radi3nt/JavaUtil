package fr.radi3nt.animations;

public class ProcessedAnimation {

    public final AnimationClip animationClip;
    private final boolean looping;
    private float relativeTime;

    public ProcessedAnimation(AnimationClip animationClip, boolean looping) {
        this.animationClip = animationClip;
        this.looping = looping;
    }

    public float getRelativeTime() {
        return relativeTime;
    }

    public void incrementTime(float delta) {
        relativeTime+=delta;
        if (relativeTime>animationClip.duration && looping)
            relativeTime-=animationClip.duration;
    }

}
