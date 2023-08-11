package fr.radi3nt.animations.importing.anim.animation;

import fr.radi3nt.animations.channels.AnimationChannel;
import fr.radi3nt.animations.importing.anim.animation.data.AnimData;

public interface AnimationChannelBuilder {

    AnimationChannel create(String inputType, String channelName, String objectName, AnimData animData);

}
