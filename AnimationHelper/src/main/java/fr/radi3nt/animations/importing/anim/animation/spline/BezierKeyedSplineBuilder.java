package fr.radi3nt.animations.importing.anim.animation.spline;

import fr.radi3nt.animations.importing.anim.animation.data.key.CurveHandleData;
import fr.radi3nt.animations.importing.anim.animation.data.key.InterpolationType;
import fr.radi3nt.animations.importing.anim.animation.data.key.KeyData;
import fr.radi3nt.spline.curve.Curve;
import fr.radi3nt.spline.curve.curves.CubicBezierCurve;
import fr.radi3nt.spline.splines.CollectionSpline;
import fr.radi3nt.spline.splines.Spline;
import fr.radi3nt.spline.splines.builder.bezier.dim1.DirectCubicBezierCurveController;
import fr.radi3nt.spline.splines.dimensions.EncapsulatingSpline2D;
import fr.radi3nt.spline.splines.dimensions.Spline2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BezierKeyedSplineBuilder implements KeyedSplineBuilder {

    private final Map<InterpolationType, CurveHandleDataBuilder> curveHandleDataBuilderMap;

    public BezierKeyedSplineBuilder(Map<InterpolationType, CurveHandleDataBuilder> curveHandleDataBuilderMap) {
        this.curveHandleDataBuilderMap = curveHandleDataBuilderMap;
    }

    @Override
    public Spline2D build(List<KeyData> keyData, float animFrameRate) {
        List<Curve> xCurves = new ArrayList<>();
        List<Curve> yCurves = new ArrayList<>();
        Spline xSpline = new CollectionSpline(xCurves);
        Spline ySpline = new CollectionSpline(yCurves);
        for (int i = 0; i < keyData.size()-1; i++) {
            KeyData start = keyData.get(i);
            KeyData end = keyData.get(i+1);

            InterpolationType startSplineType = start.getOutInterpolation();
            InterpolationType endSplineType = end.getInInterpolation();

            CurveHandleData startCurveData = curveHandleDataBuilderMap.get(startSplineType).buildStartHandleData(keyData, i, start, end);
            CurveHandleData endCurveData = curveHandleDataBuilderMap.get(endSplineType).buildEndHandleData(keyData, i, start, end);

            CubicBezierCurve curveX = new CubicBezierCurve(new DirectCubicBezierCurveController(
                    start.getIn()/animFrameRate,
                    end.getIn()/animFrameRate,
                    (start.getIn()+calcPosX(startCurveData))/animFrameRate,
                    (end.getIn()-calcPosX(endCurveData))/animFrameRate));
            CubicBezierCurve curveY = new CubicBezierCurve(new DirectCubicBezierCurveController(
                    start.getOut(),
                    end.getOut(),
                    start.getOut()+calcPosY(startCurveData),
                    end.getOut()-calcPosY(endCurveData)));
            xCurves.add(curveX);
            yCurves.add(curveY);
        }

        return new EncapsulatingSpline2D(xSpline, ySpline);
    }


    private float calcPosX(CurveHandleData curveHandleData) {
        return (float) (Math.cos(Math.toRadians(curveHandleData.getTangentAngle()))*curveHandleData.getTangentWeight());
    }

    private float calcPosY(CurveHandleData curveHandleData) {
        return (float) (Math.sin(Math.toRadians(curveHandleData.getTangentAngle()))*curveHandleData.getTangentWeight());
    }
}
