package fr.radi3nt.animations.importing.anim.animation.data.key;

public class CurveHandleData {

    private final float tangentAngle;
    private final float tangentWeight;

    public CurveHandleData(float tangentAngle, float tangentWeight) {
        this.tangentAngle = tangentAngle;
        this.tangentWeight = tangentWeight;
    }

    public float getTangentAngle() {
        return tangentAngle;
    }

    public float getTangentWeight() {
        return tangentWeight;
    }

    @Override
    public String toString() {
        return "CurveHandleData{" +
                "tangentAngle=" + tangentAngle +
                ", tangentWeight=" + tangentWeight +
                '}';
    }
}
