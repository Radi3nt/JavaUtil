package fr.radi3nt.animations.timeline.layers;

import fr.radi3nt.animations.ProcessedAnimation;

import java.util.ArrayList;
import java.util.List;

public class AnimationLayer {

    private final List<ProcessedAnimation> animations = new ArrayList<>();
    private final LayerMode layerMode;

    public AnimationLayer(LayerMode layerMode) {
        this.layerMode = layerMode;
    }


}
