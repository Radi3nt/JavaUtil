package fr.radi3nt.spline.imports.spline;

import fr.radi3nt.spline.imports.key.KeyData;
import fr.radi3nt.spline.splines.dimensions.Spline2D;

import java.util.List;

public interface KeyedSplineBuilder {

    Spline2D build(List<KeyData> keys, float intervalDuration);

}
