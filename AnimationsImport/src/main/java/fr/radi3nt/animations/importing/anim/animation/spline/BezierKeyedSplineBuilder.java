package fr.radi3nt.animations.importing.anim.animation.spline;

import fr.radi3nt.animations.importing.anim.animation.data.key.CurveHandleData;
import fr.radi3nt.animations.importing.anim.animation.data.key.InterpolationType;
import fr.radi3nt.animations.importing.anim.animation.data.key.KeyData;
import fr.radi3nt.animations.importing.anim.animation.spline.handle.AutoHandleDataBuilder;
import fr.radi3nt.animations.importing.anim.animation.spline.handle.FixedHandleDataBuilder;
import fr.radi3nt.animations.importing.anim.animation.spline.handle.GeneratedHandleDataBuilder;
import fr.radi3nt.spline.curve.Curve;
import fr.radi3nt.spline.curve.curves.bezier.CubicBezierCurve;
import fr.radi3nt.spline.splines.CollectionSpline;
import fr.radi3nt.spline.splines.Spline;
import fr.radi3nt.spline.splines.builder.bezier.dim1.DirectCubicBezierCurveController;
import fr.radi3nt.spline.splines.dimensions.EncapsulatingSpline2D;
import fr.radi3nt.spline.splines.dimensions.Spline2D;

import java.util.*;

public class BezierKeyedSplineBuilder implements KeyedSplineBuilder {


    private static final Map<InterpolationType, CurveHandleDataBuilder> DEFAULT_MAP = new HashMap<>();
    static  {
        DEFAULT_MAP.put(InterpolationType.FIXED, new FixedHandleDataBuilder());
        DEFAULT_MAP.put(InterpolationType.AUTO, new AutoHandleDataBuilder());
        DEFAULT_MAP.put(InterpolationType.SPLINE, new GeneratedHandleDataBuilder());
    }

    public static final Map<InterpolationType, CurveHandleDataBuilder> DEFAULT_INTERPOLATIONS = Collections.unmodifiableMap(DEFAULT_MAP);


    private final Map<InterpolationType, CurveHandleDataBuilder> curveHandleDataBuilderMap;
    private final float scaleX;
    private final float scaleY;

    public BezierKeyedSplineBuilder(Map<InterpolationType, CurveHandleDataBuilder> curveHandleDataBuilderMap, float scaleX, float scaleY) {
        this.curveHandleDataBuilderMap = curveHandleDataBuilderMap;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public static KeyedSplineBuilder newDefault(float scaleX, float scaleY) {
        return new BezierKeyedSplineBuilder(DEFAULT_INTERPOLATIONS, scaleX, scaleY);
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

            float cappedStartLength = capTangentLength(end.getFrameIndex()-start.getFrameIndex(), startCurveData);
            float cappedEndLength = capTangentLength(end.getFrameIndex()-start.getFrameIndex(), endCurveData);

            float startXPointControl =(start.getFrameIndex()+calcPosX(startCurveData, cappedStartLength));
            float endXPointControl = (end.getFrameIndex()-calcPosX(endCurveData, cappedEndLength));

            float startYPointControl = (start.getCorrespondingData()+calcPosY(startCurveData, cappedStartLength));
            float endYPointControl = (end.getCorrespondingData()-calcPosY(endCurveData, cappedEndLength));

            CubicBezierCurve curveX = new CubicBezierCurve(new DirectCubicBezierCurveController(
                    start.getFrameIndex()*scaleX/animFrameRate,
                    end.getFrameIndex()*scaleX/animFrameRate,
                    startXPointControl*scaleX/animFrameRate,
                    endXPointControl*scaleX/animFrameRate));
            CubicBezierCurve curveY = new CubicBezierCurve(new DirectCubicBezierCurveController(
                    start.getCorrespondingData()*scaleY,
                    end.getCorrespondingData()*scaleY,
                    startYPointControl*scaleY,
                    endYPointControl*scaleY));

            xCurves.add(curveX);
            yCurves.add(curveY);
        }

        return new EncapsulatingSpline2D(xSpline, ySpline);
    }


    private float capTangentLength(float frameDelta, CurveHandleData curveHandleData) {
        float angleProjected = (float) Math.cos(Math.toRadians(curveHandleData.getTangentAngle()));
        float maxLength = frameDelta/angleProjected;
        return Math.min(curveHandleData.getTangentWeight(), maxLength);
    }

    private float calcPosX(CurveHandleData curveHandleData, float length) {
        return (float) (Math.cos(Math.toRadians(curveHandleData.getTangentAngle()))*length);
    }

    private float calcPosY(CurveHandleData curveHandleData, float length) {
        return (float) (Math.sin(Math.toRadians(curveHandleData.getTangentAngle()))*length);
    }
}
