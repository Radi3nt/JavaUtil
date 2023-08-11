package fr.radi3nt.animations.importing.anim.animation.spline;

import fr.radi3nt.animations.importing.anim.animation.data.key.CurveHandleData;
import fr.radi3nt.animations.importing.anim.animation.data.key.KeyData;

import java.util.List;

public interface CurveHandleDataBuilder {

    CurveHandleData buildStartHandleData(List<KeyData> keyData, int i, KeyData start, KeyData end);
    CurveHandleData buildEndHandleData(List<KeyData> keyData, int i, KeyData start, KeyData end);

}
