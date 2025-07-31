package fr.radi3nt.spline.imports.spline.handle;

import fr.radi3nt.spline.imports.key.CurveHandleData;
import fr.radi3nt.spline.imports.key.KeyData;
import fr.radi3nt.spline.imports.spline.CurveHandleDataBuilder;
import fr.radi3nt.maths.components.vectors.Vector2f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector2f;

import java.util.List;

public class GeneratedHandleDataBuilder implements CurveHandleDataBuilder {
    @Override
    public CurveHandleData buildStartHandleData(List<KeyData> keyData, int i, KeyData start, KeyData end) {
        Vector2f endPos = new SimpleVector2f(end.getX(), end.getY());
        Vector2f beforeOnePos = new SimpleVector2f(start.getX(), end.getY());

        float closeness = 0.5f;
        float lengthMultiplier = 2;
        if (i!=0) {
            lengthMultiplier = 1f;
            KeyData startBefore = keyData.get(i-1);
            beforeOnePos = new SimpleVector2f(startBefore.getX(), startBefore.getY());
            closeness = (start.getX()-beforeOnePos.getX())/(endPos.getX()-beforeOnePos.getX());
        }

        Vector2f direction = endPos.clone().sub(beforeOnePos.clone());
        float dirLength = direction.length();
        float angle = getHandleAngle(direction, dirLength);

        return new CurveHandleData(angle, getTangentWeight(1 - closeness, dirLength, lengthMultiplier));
    }

    @Override
    public CurveHandleData buildEndHandleData(List<KeyData> keyData, int i, KeyData start, KeyData end) {
        Vector2f startPos = new SimpleVector2f(start.getX(), start.getY());
        Vector2f afterEndPos = new SimpleVector2f(end.getX(), end.getY());

        float closeness = 0.5f;
        float lengthMultiplier = 2;

        if (i<keyData.size()-2) {
            lengthMultiplier=1;
            KeyData endAfter = keyData.get(i+2);
            afterEndPos = new SimpleVector2f(endAfter.getX(), endAfter.getY());
            closeness = (end.getX()-startPos.getX())/(afterEndPos.getX()-startPos.getX());
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
