package fr.radi3nt.loghelper.archiving.formatters.comp;

import fr.radi3nt.loghelper.archiving.formatters.LogFormatter;
import fr.radi3nt.loghelper.logs.actions.LogAction;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DateFormatterComponent implements FormatComponent {

    private final DateTimeFormatter dateTimeFormatter;

    public DateFormatterComponent(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public String format(LogAction logAction) {
        String date = getDateString(logAction);
        return "[" + date + "] ";
    }

    private String getDateString(LogAction logAction) {
        LocalDateTime triggerTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(logAction.getTimeStamp()), TimeZone.getDefault().toZoneId());
        return dateTimeFormatter.format(triggerTime);
    }

}
