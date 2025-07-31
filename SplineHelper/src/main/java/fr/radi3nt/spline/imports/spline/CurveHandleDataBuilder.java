package fr.radi3nt.spline.imports.spline;

import fr.radi3nt.spline.imports.key.CurveHandleData;
import fr.radi3nt.spline.imports.key.KeyData;

import java.util.List;

public interface CurveHandleDataBuilder {

    CurveHandleData buildStartHandleData(List<KeyData> keyData, int i, KeyData start, KeyData end);
    CurveHandleData buildEndHandleData(List<KeyData> keyData, int i, KeyData start, KeyData end);

}
