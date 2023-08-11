package fr.radi3nt.animations.importing.anim.animation.spline.handle;

import fr.radi3nt.animations.importing.anim.animation.data.key.CurveHandleData;
import fr.radi3nt.animations.importing.anim.animation.data.key.KeyData;
import fr.radi3nt.animations.importing.anim.animation.spline.CurveHandleDataBuilder;
import fr.radi3nt.maths.components.vectors.Vector2f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector2f;

import java.util.List;

public class GeneratedHandleDataBuilder implements CurveHandleDataBuilder {
    @Override
    public CurveHandleData buildStartHandleData(List<KeyData> keyData, int i, KeyData start, KeyData end) {
        Vector2f endPos = new SimpleVector2f(end.getIn(), end.getOut());
        Vector2f beforeOnePos = new SimpleVector2f(start.getIn(), end.getOut());

        float closeness = 0.5f;
        float lengthMultiplier = 2;
        if (i!=0) {
            lengthMultiplier = 1f;
            KeyData startBefore = keyData.get(i-1);
            beforeOnePos = new SimpleVector2f(startBefore.getIn(), startBefore.getOut());
            closeness = (start.getIn()-beforeOnePos.getX())/(endPos.getX()-beforeOnePos.getX());
        }

        Vector2f direction = endPos.clone().sub(beforeOnePos.clone());
        float dirLength = direction.length();
        float angle = getHandleAngle(direction, dirLength);

        return new CurveHandleData(angle, getTangentWeight(1 - closeness, dirLength, lengthMultiplier));
    }

    @Override
    public CurveHandleData buildEndHandleData(List<KeyData> keyData, int i, KeyData start, KeyData end) {
        Vector2f startPos = new SimpleVector2f(start.getIn(), start.getOut());
        Vector2f afterEndPos = new SimpleVector2f(end.getIn(), end.getOut());

        float closeness = 0.5f;
        float lengthMultiplier = 2;

        if (i<keyData.size()-2) {
            lengthMultiplier=1;
            KeyData endAfter = keyData.get(i+2);
            afterEndPos = new SimpleVector2f(endAfter.getIn(), endAfter.getOut());
            closeness = (end.getIn()-startPos.getX())/(afterEndPos.getX()-startPos.getX());
        }

        Vector2f direction = afterEndPos.clone().sub(startPos.clone());
        float dirLength = direction.length();
        float angle = getHandleAngle(direction, dirLength);

        return new CurveHandleData(angle, getTangentWeight(closeness, dirLength, lengthMultiplier));
    }

    protected float getTangentWeight(float closeness, float length, float lengthMultiplier) {
        return closeness * length/3f * lengthMultiplier;
    }

    protected float getHandleAngle(Vector2f direction, float dirLength) {
        direction.div(dirLength);
        return (float) (Math.toDegrees(Math.atan2(direction.getY(), direction.getX())));
    }
}
