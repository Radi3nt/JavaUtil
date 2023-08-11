package fr.radi3nt.animations.importing.anim.animation.data.key;

import fr.radi3nt.spline.splines.dimensions.Spline2D;

import java.util.List;

public class KeyframesData {

    private final List<KeyData> keyData;
    private final Spline2D dataSpline;

    public KeyframesData(List<KeyData> keyData, Spline2D dataSpline) {
        this.keyData = keyData;
        this.dataSpline = dataSpline;
    }

    public List<KeyData> getKeyData() {
        return keyData;
    }

    public Spline2D getDataSpline() {
        return dataSpline;
    }

    @Override
    public String toString() {
        return "KeyframesData{" +
                "keyData=" + keyData +
                ", dataSpline=" + dataSpline +
                '}';
    }
}
