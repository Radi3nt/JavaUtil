package fr.radi3nt.animations.importing.anim.animation.spline.handle;

import fr.radi3nt.animations.importing.anim.animation.data.key.CurveHandleData;
import fr.radi3nt.animations.importing.anim.animation.data.key.KeyData;
import fr.radi3nt.animations.importing.anim.animation.spline.CurveHandleDataBuilder;

import java.util.List;

public class FixedHandleDataBuilder implements CurveHandleDataBuilder {
    @Override
    public CurveHandleData buildStartHandleData(List<KeyData> keyData, int i, KeyData start, KeyData end) {
        return start.getCurveHandleData()[start.getCurveHandleData().length-1];
    }

    @Override
    public CurveHandleData buildEndHandleData(List<KeyData> keyData, int i, KeyData start, KeyData end) {
         return end.getCurveHandleData()[0];
    }
}
