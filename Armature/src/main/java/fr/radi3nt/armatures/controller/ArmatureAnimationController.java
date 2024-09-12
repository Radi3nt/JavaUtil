package fr.radi3nt.armatures.controller;

import fr.radi3nt.animations.channels.ChannelIdentifier;
import fr.radi3nt.animations.timeline.AnimationTimeline;
import fr.radi3nt.armatures.armature.driver.BoneDriver;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;

public class ArmatureAnimationController {

    private final AnimationTimeline animationTimeline;
    private final ArmatureController controller;

    public ArmatureAnimationController(AnimationTimeline animationTimeline, ArmatureController controller) {
        this.animationTimeline = animationTimeline;
        this.controller = controller;
    }

    public void update() {
        for (String availableObject : animationTimeline.getAvailableObjects()) {
            BoneDriver driver = controller.getDriver(availableObject);
            if (driver==null) {
                System.err.println("Unable to find object '" + availableObject + "' referenced in the animation clip, ignoring");
                continue;
            }
            Quaternion rotate = animationTimeline.getAnimatedPropertyOrDefault(ChannelIdentifier.rotation(availableObject), null);
            Vector3f translate = animationTimeline.getAnimatedPropertyOrDefault(ChannelIdentifier.translation(availableObject), null);
            Vector3f scale = animationTimeline.getAnimatedPropertyOrDefault(ChannelIdentifier.scale(availableObject), null);

            if (rotate!=null)
                driver.set(rotate);
            if (translate!=null)
                driver.set(translate);
        }
    }
}
