package fr.radi3nt.animations.importing.anim.animation.spline;

import fr.radi3nt.animations.importing.anim.animation.data.key.KeyData;
import fr.radi3nt.spline.splines.dimensions.Spline2D;

import java.util.List;

public interface KeyedSplineBuilder {

    Spline2D build(List<KeyData> keys, float framesPerSeconds);

}
