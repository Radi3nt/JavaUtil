package fr.radi3nt.animations.timeline.blending;

import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;

public class QuaternionBlending implements ObjectBlending<Quaternion> {
    @Override
    public Quaternion blend(Quaternion first, Quaternion second, float secondWeight) {
        if (first==null)
            first = ComponentsQuaternion.zero();
        Quaternion result = ComponentsQuaternion.zero();
        result.copy(first);
        result.interpolate(second, secondWeight);

        return result;
    }

    @Override
    public Quaternion add(Quaternion first, Quaternion second, float weight) {
        if (first==null)
            first = ComponentsQuaternion.zero();

        Quaternion interpolation = ComponentsQuaternion.zero();
        interpolation.interpolate(second, weight);

        Quaternion result = ComponentsQuaternion.zero();
        result.copy(first);
        result.multiply(interpolation);

        return result;
    }

    @Override
    public Class<Quaternion>[] supported() {
        return new Class[] {Quaternion.class, ComponentsQuaternion.class};
    }
}
