package fr.radi3nt.animations.importing.anim.animation.data.key;

import java.util.Arrays;

public class KeyData {

    private final float frameIndex;
    private final float correspondingData;

    private final InterpolationType inInterpolation;
    private final InterpolationType outInterpolation;

    private final boolean tangentLocked;
    private final boolean weightLocked;

    private final CurveHandleData[] curveHandleData;


    public KeyData(float frameIndex, float correspondingData, InterpolationType inInterpolation, InterpolationType outInterpolation, boolean tangentLocked, boolean weightLocked, CurveHandleData[] curveHandleData) {
        this.frameIndex = frameIndex;
        this.correspondingData = correspondingData;
        this.inInterpolation = inInterpolation;
        this.outInterpolation = outInterpolation;
        this.tangentLocked = tangentLocked;
        this.weightLocked = weightLocked;
        this.curveHandleData = curveHandleData;
    }

    public float getFrameIndex() {
        return frameIndex;
    }

    public float getCorrespondingData() {
        return correspondingData;
    }

    public InterpolationType getInInterpolation() {
        return inInterpolation;
    }

    public InterpolationType getOutInterpolation() {
        return outInterpolation;
    }

    public boolean isTangentLocked() {
        return tangentLocked;
    }

    public boolean isWeightLocked() {
        return weightLocked;
    }

    public CurveHandleData[] getCurveHandleData() {
        return curveHandleData;
    }

    @Override
    public String toString() {
        return "KeyData{" +
                "frameIndex=" + frameIndex +
                ", correspondingData=" + correspondingData +
                ", inInterpolation=" + inInterpolation +
                ", outInterpolation=" + outInterpolation +
                ", tangentLocked=" + tangentLocked +
                ", weightLocked=" + weightLocked +
                ", curveHandleData=" + Arrays.toString(curveHandleData) +
                '}';
    }
}
