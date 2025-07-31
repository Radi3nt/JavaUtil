package fr.radi3nt.animations.channels.types;

import fr.radi3nt.spline.interpolation.InterpolationData;
import fr.radi3nt.maths.components.advanced.matrix.angle.JavaMathAngle;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;

public class KeyframeSplitEulerXYZRotationChannel extends KeyframeSplitInterpolationDataChannel<Quaternion> {

    private final float relativeDuration;

    public KeyframeSplitEulerXYZRotationChannel(InterpolationData[] interpolationData, float relativeDuration) {
        super(interpolationData);
        this.relativeDuration = relativeDuration;
    }

    @Override
    public Quaternion transform(float t) {
        float transformedTime = t/ relativeDuration;
        float x = interpolationData[0]==null ? 0 : interpolationData[0].interpolate(transformedTime);
        float y = interpolationData[1]==null ? 0 : interpolationData[1].interpolate(transformedTime);
        float z = interpolationData[2]==null ? 0 : interpolationData[2].interpolate(transformedTime);

        return ComponentsQuaternion.fromEulerAngles(JavaMathAngle.fromDegree(x), JavaMathAngle.fromDegree(y), JavaMathAngle.fromDegree(z));
    }
}
