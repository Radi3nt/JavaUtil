package fr.radi3nt.animations.importing.anim.animation.data.key;

import java.util.Arrays;

public class KeyData {

    private final int in;
    private final float out;

    private final InterpolationType inInterpolation;
    private final InterpolationType outInterpolation;

    private final boolean tangentLocked;
    private final boolean weightLocked;

    private final CurveHandleData[] curveHandleData;


    public KeyData(int in, float out, InterpolationType inInterpolation, InterpolationType outInterpolation, boolean tangentLocked, boolean weightLocked, CurveHandleData[] curveHandleData) {
        this.in = in;
        this.out = out;
        this.inInterpolation = inInterpolation;
        this.outInterpolation = outInterpolation;
        this.tangentLocked = tangentLocked;
        this.weightLocked = weightLocked;
        this.curveHandleData = curveHandleData;
    }

    public int getIn() {
        return in;
    }

    public float getOut() {
        return out;
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
                "in=" + in +
                ", out=" + out +
                ", inInterpolation=" + inInterpolation +
                ", outInterpolation=" + outInterpolation +
                ", tangentLocked=" + tangentLocked +
                ", weightLocked=" + weightLocked +
                ", curveHandleData=" + Arrays.toString(curveHandleData) +
                '}';
    }
}
