package fr.radi3nt.animations.importing.anim.animation;

import fr.radi3nt.animations.importing.anim.animation.data.AnimData;
import fr.radi3nt.animations.importing.anim.animation.data.ChannelInfo;

import java.util.Map;

public class AnimationBody {

    private final Map<ChannelInfo, AnimData> animData;

    public AnimationBody(Map<ChannelInfo, AnimData> animData) {
        this.animData = animData;
    }

    public Map<ChannelInfo, AnimData> getAnimData() {
        return animData;
    }

    @Override
    public String toString() {
        return "AnimationBody{" +
                "animData=" + animData +
                '}';
    }
}
