package fr.radi3nt.animations.channels.types;

import fr.radi3nt.animations.channels.KeyframeChannel;
import fr.radi3nt.spline.interpolation.InterpolationData;

public abstract class KeyframeSplitInterpolationDataChannel<A> implements KeyframeChannel<A> {

    protected final InterpolationData[] interpolationData;

    protected KeyframeSplitInterpolationDataChannel(InterpolationData[] interpolationData) {
        this.interpolationData = interpolationData;
    }

    public InterpolationData[] getInterpolationData() {
        return interpolationData;
    }
}
