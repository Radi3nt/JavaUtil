package fr.radi3nt.spline.imports.spline.handle;

import fr.radi3nt.spline.imports.key.CurveHandleData;
import fr.radi3nt.spline.imports.key.KeyData;
import fr.radi3nt.spline.imports.spline.CurveHandleDataBuilder;

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
