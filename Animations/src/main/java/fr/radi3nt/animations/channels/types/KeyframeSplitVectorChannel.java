package fr.radi3nt.animations.channels.types;

import fr.radi3nt.spline.interpolation.InterpolationData;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class KeyframeSplitVectorChannel extends KeyframeSplitInterpolationDataChannel<Vector3f> {

    private final float relativeDuration;

    public KeyframeSplitVectorChannel(InterpolationData[] interpolationData, float relativeDuration) {
        super(interpolationData);
        this.relativeDuration = relativeDuration;
    }

    @Override
    public Vector3f transform(float t) {
        float transformedTime = t/ relativeDuration;
        float x = interpolationData[0].interpolate(transformedTime);
        float y = interpolationData[1].interpolate(transformedTime);
        float z = interpolationData[2].interpolate(transformedTime);
        return new SimpleVector3f(x, y, z);
    }

}
