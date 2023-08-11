package fr.radi3nt.animations.timeline.interpolation.timing;

import fr.radi3nt.maths.components.vectors.Vector2f;
import fr.radi3nt.spline.splines.dimensions.Spline2D;

public class Spline2DInterpolationTiming implements InterpolationTiming {

    private static final float X_TOLERANCE = 0.001f;
    private static final int MAX_LOOP = 10_000;
    private final Spline2D spline;
    private final float splineLength;
    private final int keyAmount;

    private final float startState;
    private final float endState;

    public Spline2DInterpolationTiming(Spline2D spline, float splineLength, int keyAmount) {
        this.spline = spline;
        this.splineLength = splineLength;
        this.keyAmount = keyAmount;
        endState = spline.interpolate(keyAmount).getY();
        startState = spline.interpolate(0).getY();
    }

    public Spline2DInterpolationTiming(Spline2D spline, float splineLength) {
        this(spline, splineLength, 1);
    }

    public Spline2DInterpolationTiming(Spline2D spline) {
        this(spline, 1f);
    }

    @Override
    public float getTransformedTime(float time) {
        if (time>=1f)
            return endState;
        if (time<=0f)
            return startState;

        Vector2f point = findPointWithX(time*splineLength);
        return point.getY();
    }

    private Vector2f findPointWithX(float xTarget) {

        float lower = 0;
        float upper = keyAmount;
        float percent = (upper + lower) / 2;

        //get initial x
        Vector2f result = spline.interpolate(percent);
        float x = result.getX();

        //loop until completion
        int loopAmount = 0;
        while(Math.abs(xTarget - x) > X_TOLERANCE && loopAmount<MAX_LOOP) {
            if(xTarget > x)
                lower = percent;
            else
                upper = percent;

            percent = (upper + lower) / 2;
            result = spline.interpolate(percent);
            x = result.getX();
            loopAmount++;
        }

        if (loopAmount==MAX_LOOP)
            System.out.println("Exceeding for time " + xTarget);

        return result;
    }
}
