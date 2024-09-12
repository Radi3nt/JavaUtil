package fr.radi3nt.animations.importing.anim.animation;

import fr.radi3nt.animations.importing.anim.content.AnimFileContent;
import fr.radi3nt.animations.importing.anim.header.AnimHeader;

public interface AnimationBodyBuilder {

    AnimationBody build(AnimFileContent content, AnimHeader header);

}
