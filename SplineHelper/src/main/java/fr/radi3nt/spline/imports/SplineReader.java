package fr.radi3nt.spline.imports;

import fr.radi3nt.spline.imports.key.CurveHandleData;
import fr.radi3nt.spline.imports.key.InterpolationType;
import fr.radi3nt.spline.imports.key.KeyData;
import fr.radi3nt.spline.imports.spline.KeyedSplineBuilder;
import fr.radi3nt.spline.splines.dimensions.Spline2D;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SplineReader {

    private final KeyedSplineBuilder splineBuilder;

    public SplineReader(KeyedSplineBuilder splineBuilder) {
        this.splineBuilder = splineBuilder;
    }

    public Spline2D build(BufferedReader content, float scaleX) throws IOException {
        return splineBuilder.build(getKeyData(content), scaleX);
    }

    private List<KeyData> getKeyData(BufferedReader content) throws IOException {
        List<KeyData> keyData = new ArrayList<>();
        String line;
        while ((line = content.readLine())!=null) {
            line = formatLine(line);
            if (line.isEmpty())
                continue;
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
        return keyData;
    }

    private String formatLine(String s) {
        String formatted = s.trim();
        int index = formatted.lastIndexOf(";");
        if (index!=-1)
            formatted = formatted.substring(0, index);
        return formatted;
    }

    private boolean stringToBool(String str) {
        return str.equals("1");
    }

}
