package fr.radi3nt.animations.channels.interpolation;

import fr.radi3nt.spline.splines.dimensions.Spline2D;

import static java.lang.Math.abs;

public class PlotInterpolationData implements InterpolationData {

    private static final float EPSILON = 1e-5f;
    private final float[] plotted;
    private final float plotInterval;

    public PlotInterpolationData(float[] plotted, float plotInterval) {
        this.plotted = plotted;
        this.plotInterval = plotInterval;
    }

    public static PlotInterpolationData create(Spline2D spline, float splineDuration, int splineSegments, float splineOffset, int points) {
        float[] plots = new float[points];
        float lastT = 0;

        float plotInterval = splineDuration/(points-1f);

        for (int i = 0; i < points; i++) {
            float x = i*plotInterval + splineOffset;
            float correspondingT = lastT = newtonRaphsonMethod(x, lastT, spline, splineSegments);
            plots[i] = spline.interpolateY(correspondingT);
        }
        return new PlotInterpolationData(plots, plotInterval);
    }

    private static float newtonRaphsonMethod(float expectedX, float startT, Spline2D spline, int splineSegments) {
        float currentGuess = startT;
        for (int i = 0; i < 50; i++) {
            if (currentGuess>=splineSegments)
                currentGuess = splineSegments;
            if (currentGuess<0)
                currentGuess = 0;
            float value = spline.interpolateX(currentGuess);
            if (closeEnough(value, expectedX)) {
                break;
            }
            float vel = spline.velocityX(currentGuess);
            if (vel==0)
                vel = EPSILON; //preventing catastrophic failure
            currentGuess = currentGuess - (value-expectedX)/(vel);
        }

        if (currentGuess>=splineSegments)
            currentGuess = splineSegments;
        if (currentGuess<0)
            currentGuess = 0;

        return currentGuess;
    }

    private static boolean closeEnough(float currentGuess, float expectedX) {
        return abs(currentGuess-expectedX)<EPSILON;
    }

    @Override
    public float interpolate(float t) {
        int index = (int) Math.floor(t/plotInterval);
        int indexPlusOne = index+1;

        if (index<0)
            return plotted[0];
        if (index>=plotted.length)
            return plotted[plotted.length-1];

        if (indexPlusOne>=plotted.length)
            return plotted[index];

        float startValue = plotted[index];
        float endValue = plotted[indexPlusOne];
        float relativeTime = (t-index*plotInterval)/plotInterval;

        return relativeTime*endValue+(1-relativeTime)*startValue;
    }
}
