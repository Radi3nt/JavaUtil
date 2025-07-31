package fr.radi3nt.spline.imports.spline;

import fr.radi3nt.spline.imports.key.CurveHandleData;
import fr.radi3nt.spline.imports.key.InterpolationType;
import fr.radi3nt.spline.imports.key.KeyData;
import fr.radi3nt.spline.imports.spline.handle.AutoHandleDataBuilder;
import fr.radi3nt.spline.imports.spline.handle.FixedHandleDataBuilder;
import fr.radi3nt.spline.imports.spline.handle.GeneratedHandleDataBuilder;
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
    private final float offsetX;
    private final float offsetY;
    private final float scaleX;
    private final float scaleY;

    public BezierKeyedSplineBuilder(Map<InterpolationType, CurveHandleDataBuilder> curveHandleDataBuilderMap, float offsetX, float offsetY, float scaleX, float scaleY) {
        this.curveHandleDataBuilderMap = curveHandleDataBuilderMap;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public static KeyedSplineBuilder newDefault(float scaleX, float scaleY) {
        return new BezierKeyedSplineBuilder(DEFAULT_INTERPOLATIONS, 0, 0, scaleX, scaleY);
    }

    public static KeyedSplineBuilder newDefault(float offsetX, float offsetY, float scaleX, float scaleY) {
        return new BezierKeyedSplineBuilder(DEFAULT_INTERPOLATIONS, offsetX, offsetY, scaleX, scaleY);
    }

    @Override
    public Spline2D build(List<KeyData> keyData, float scaleX) {
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

            float cappedStartLength = capTangentLength(end.getX()-start.getX(), startCurveData);
            float cappedEndLength = capTangentLength(end.getX()-start.getX(), endCurveData);

            float startXPointControl =(start.getX()+calcPosX(startCurveData, cappedStartLength));
            float endXPointControl = (end.getX()-calcPosX(endCurveData, cappedEndLength));

            float startYPointControl = (start.getY()+calcPosY(startCurveData, cappedStartLength));
            float endYPointControl = (end.getY()-calcPosY(endCurveData, cappedEndLength));

            CubicBezierCurve curveX = new CubicBezierCurve(new DirectCubicBezierCurveController(
                    start.getX()* this.scaleX *scaleX+offsetX,
                    end.getX()* this.scaleX *scaleX+offsetX,
                    startXPointControl* this.scaleX *scaleX+offsetX,
                    endXPointControl* this.scaleX *scaleX+offsetX));
            CubicBezierCurve curveY = new CubicBezierCurve(new DirectCubicBezierCurveController(
                    start.getY()*scaleY+offsetY,
                    end.getY()*scaleY+offsetY,
                    startYPointControl*scaleY+offsetY,
                    endYPointControl*scaleY+offsetY));

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
