package fr.radi3nt.updater.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeUtil {

    public static LocalDateTime convert(String date) {
        if (date==null)
            return null;

        return LocalDateTime.ofInstant(Instant.parse(date), ZoneOffset.UTC);
    }

}
