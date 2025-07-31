package fr.radi3nt.animations.timeline.layers;

import fr.radi3nt.animations.timeline.AnimationTimeline;
import fr.radi3nt.animations.timeline.SetAnimationTimeline;

public class AnimationLayer {

    private final AnimationTimeline timeline;
    private final LayerBlending layerMode;

    public AnimationLayer(AnimationTimeline timeline, LayerBlending layerMode) {
        this.timeline = timeline;
        this.layerMode = layerMode;
    }

    public AnimationLayer(LayerBlending layerMode) {
        this(new SetAnimationTimeline(), layerMode);
    }

    public LayerBlending getLayerMode() {
        return layerMode;
    }

    public AnimationTimeline getTimeline() {
        return timeline;
    }
}
