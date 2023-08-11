package fr.radi3nt.animations.importing.anim.header;

import fr.radi3nt.animations.importing.anim.content.AnimFileContent;
import fr.radi3nt.animations.importing.anim.units.AngularUnit;
import fr.radi3nt.animations.importing.anim.units.LinearUnit;
import fr.radi3nt.animations.importing.anim.units.TimeUnit;

import java.util.HashMap;
import java.util.Map;

public class FailureSafeAnimHeaderBuilder implements AnimHeaderBuilder {

    private static final String HEADER_STOP_SEGMENT = "anim ";

    private static final String VERSION_PROPERTY_ID = "animVersion";
    private static final String TIME_UNIT_PROPERTY_ID = "timeUnit";
    private static final String LINEAR_UNIT_PROPERTY_ID = "linearUnit";
    private static final String ANGULAR_UNIT_PROPERTY_ID = "angularUnit";
    private static final String START_TIME_PROPERTY_ID = "startTime";
    private static final String END_TIME_PROPERTY_ID = "endTime";

    @Override
    public AnimHeader build(AnimFileContent content) {
        Map<String, String> propertyAndValue = new HashMap<>();
        fillHeaderProperties(content, propertyAndValue);

        String version = propertyAndValue.getOrDefault(VERSION_PROPERTY_ID, "1.0");
        TimeUnit timeUnit = TimeUnit.valueOf(propertyAndValue.getOrDefault(TIME_UNIT_PROPERTY_ID, "ntsc").toUpperCase());
        LinearUnit linearUnit = LinearUnit.valueOf(propertyAndValue.getOrDefault(LINEAR_UNIT_PROPERTY_ID, "m").toUpperCase());
        AngularUnit angularUnit = AngularUnit.valueOf(propertyAndValue.getOrDefault(ANGULAR_UNIT_PROPERTY_ID, "rad").toUpperCase());
        int startTime = Integer.parseInt(propertyAndValue.getOrDefault(START_TIME_PROPERTY_ID, "0"));
        int endTime = Integer.parseInt(propertyAndValue.getOrDefault(END_TIME_PROPERTY_ID, "0"));

        return new AnimHeader(version, timeUnit, linearUnit, angularUnit, startTime, endTime);
    }

    private static void fillHeaderProperties(AnimFileContent content, Map<String, String> propertyAndValue) {
        while (content.hasLine()) {
            String peak = content.peakLine();
            if (peak.startsWith(HEADER_STOP_SEGMENT))
                break;
            String[] propertyAndValueString = peak.split(" ");
            propertyAndValue.put(propertyAndValueString[0], propertyAndValueString[1]);
            content.nextLine();
        }
    }
}
