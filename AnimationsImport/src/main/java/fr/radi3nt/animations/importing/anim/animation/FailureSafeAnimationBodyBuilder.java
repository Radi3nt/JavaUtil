package fr.radi3nt.animations.importing.anim.animation;

import fr.radi3nt.animations.importing.anim.animation.data.AnimData;
import fr.radi3nt.animations.importing.anim.animation.data.ChannelInfo;
import fr.radi3nt.animations.importing.anim.animation.data.key.CurveHandleData;
import fr.radi3nt.animations.importing.anim.animation.data.key.InterpolationType;
import fr.radi3nt.animations.importing.anim.animation.data.key.KeyData;
import fr.radi3nt.animations.importing.anim.animation.data.key.KeyframesData;
import fr.radi3nt.animations.importing.anim.animation.spline.KeyedSplineBuilder;
import fr.radi3nt.animations.importing.anim.content.AnimFileContent;
import fr.radi3nt.animations.importing.anim.header.AnimHeader;
import fr.radi3nt.animations.importing.anim.units.UnitType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FailureSafeAnimationBodyBuilder implements AnimationBodyBuilder {

    private static final String ANIM_STOP_SEGMENT = "keys";
    private static final String INPUT_YPE_PROPERTY_ID = "input";
    private static final String OUTPUT_TYPE_PROPERTY_ID = "output";
    private static final String WEIGHTED_PROPERTY_ID = "weighted";
    private final KeyedSplineBuilder splineBuilder;

    public FailureSafeAnimationBodyBuilder(KeyedSplineBuilder splineBuilder) {
        this.splineBuilder = splineBuilder;
    }

    @Override
    public AnimationBody build(AnimFileContent content, AnimHeader header) {
        Map<ChannelInfo, AnimData> animDataMap = new HashMap<>();
        while (content.hasLine()) {
            String line = content.readLine();
            String[] splitLine = line.split(" ");

            if (splitLine.length<=5)
                continue;


            String fullChannelType = splitLine[1];
            String mainChannelType = fullChannelType.split("\\.")[0];
            String leafChannelType = splitLine[2];

            String objectName = splitLine[3];

            int attributeInt = Integer.parseInt(splitLine[6]);

            AnimData animData = buildAnimData(content, header);
            animDataMap.put(new ChannelInfo(fullChannelType, mainChannelType, leafChannelType, attributeInt, objectName), animData);
        }

        return new AnimationBody(animDataMap);
    }

    private AnimData buildAnimData(AnimFileContent content, AnimHeader header) {
        Map<String, String> propertyAndValue = new HashMap<>();
        fillAnimProperties(content, propertyAndValue);

        UnitType inputType = UnitType.valueOf(propertyAndValue.getOrDefault(INPUT_YPE_PROPERTY_ID, "time").toUpperCase());
        UnitType outputType = UnitType.valueOf(propertyAndValue.getOrDefault(OUTPUT_TYPE_PROPERTY_ID, "time").toUpperCase());
        boolean weighted = stringToBool(propertyAndValue.getOrDefault(WEIGHTED_PROPERTY_ID, "0"));

        KeyframesData keyframesData = buildKeysData(content, header);
        return new AnimData(inputType, outputType, null, null, weighted, keyframesData);
    }

    private static void fillAnimProperties(AnimFileContent content, Map<String, String> propertyAndValue) {
        while (content.hasLine()) {
            String peak = content.readLine();
            if (peak.startsWith(ANIM_STOP_SEGMENT))
                break;
            String[] propertyAndValueString = peak.split(" ");
            propertyAndValue.put(propertyAndValueString[0], propertyAndValueString[1]);
        }
    }

    private KeyframesData buildKeysData(AnimFileContent content, AnimHeader header) {
        List<KeyData> keyData = new ArrayList<>();
        while (content.hasLine()) {
            String line = content.readLine();
            if (line.startsWith("}"))
                break;
            String[] splitLine = line.split(" ");
            float frameIndex = Float.parseFloat(splitLine[0]);
            float correspondingValue = Float.parseFloat(splitLine[1]);
            InterpolationType inInterpolation = InterpolationType.valueOf(splitLine[2].toUpperCase());
            InterpolationType outInterpolation = InterpolationType.valueOf(splitLine[3].toUpperCase());
            boolean tanLocked = stringToBool(splitLine[4]);
            boolean weightLocked = stringToBool(splitLine[5]);

            int additionalDataAmount = splitLine.length-7;
            int curveHandleDataAmount = Math.floorDiv(additionalDataAmount, 2);
            CurveHandleData[] curveHandleData = new CurveHandleData[curveHandleDataAmount];
            for (int i = 0; i < curveHandleDataAmount; i++) {
                String tanAngle = splitLine[7+i*2];
                String tanWeight = splitLine[8+i*2];
                curveHandleData[i] = new CurveHandleData(Float.parseFloat(tanAngle), Float.parseFloat(tanWeight));
            }

            keyData.add(new KeyData(frameIndex, correspondingValue, inInterpolation, outInterpolation, tanLocked, weightLocked, curveHandleData));
        }
        content.nextLine();
        return new KeyframesData(keyData, splineBuilder.build(keyData, header.getTimeUnit().getFrameEquivalent()));
    }

    private boolean stringToBool(String str) {
        return str.equals("1");
    }
}
