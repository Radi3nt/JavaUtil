package fr.radi3nt.spline.imports.key;

import java.util.Arrays;

public class KeyData {

    private final float x;
    private final float y;

    private final InterpolationType inInterpolation;
    private final InterpolationType outInterpolation;

    private final boolean tangentLocked;
    private final boolean weightLocked;

    private final CurveHandleData[] curveHandleData;


    public KeyData(float x, float y, InterpolationType inInterpolation, InterpolationType outInterpolation, boolean tangentLocked, boolean weightLocked, CurveHandleData[] curveHandleData) {
        this.x = x;
        this.y = y;
        this.inInterpolation = inInterpolation;
        this.outInterpolation = outInterpolation;
        this.tangentLocked = tangentLocked;
        this.weightLocked = weightLocked;
        this.curveHandleData = curveHandleData;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
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
                "x=" + x +
                ", y=" + y +
                ", inInterpolation=" + inInterpolation +
                ", outInterpolation=" + outInterpolation +
                ", tangentLocked=" + tangentLocked +
                ", weightLocked=" + weightLocked +
                ", curveHandleData=" + Arrays.toString(curveHandleData) +
                '}';
    }
}
