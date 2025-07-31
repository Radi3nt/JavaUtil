package fr.radi3nt.animations.channels.types;

import fr.radi3nt.animations.channels.KeyframeChannel;
import fr.radi3nt.spline.interpolation.InterpolationData;
import fr.radi3nt.maths.components.vectors.Vector3f;

public class KeyframeTranslateChannel implements KeyframeChannel<Vector3f> {

    private final InterpolationData interpolationData;
    private final Vector3f[] positions;
    private final float[] times;

    public KeyframeTranslateChannel(InterpolationData interpolationData, Vector3f[] positions, float[] times) {
        this.interpolationData = interpolationData;
        this.positions = positions;
        this.times = times;
    }

    @Override
    public Vector3f transform(float t) {
        float lastTime = 0;
        int lastTimeIndex = 0;
        float nextTime = 0;
        for (int i = 0; i < times.length; i++) {
            float time = times[i];
            nextTime = time;
            if (time > t)
                break;
            lastTime = time;
            lastTimeIndex = i;
        }
        if (lastTimeIndex==positions.length)
            return positions[positions.length-1];

        int nextTimeIndex = lastTimeIndex+1;

        float duration = nextTime - lastTime;

        float interpolated = interpolationData.interpolate((t-lastTime) / duration);

        Vector3f start = positions[lastTimeIndex];
        Vector3f end = positions[nextTimeIndex];

        return start.duplicate().mul(1-interpolated).add(end.duplicate().mul(interpolated));
    }

}
