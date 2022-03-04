package fr.radi3nt.loghelper.archiving;

import fr.radi3nt.file.files.WritableFile;
import fr.radi3nt.loghelper.archiving.formatters.LogFormatter;
import fr.radi3nt.loghelper.logs.logs.Log;
import fr.radi3nt.loghelper.logs.actions.LogAction;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LogArchiver {
    
    private final LogFilter logFilter;
    private final LogFormatter logFormatter;
    private final WritableFile writableFile;

    public LogArchiver(LogFilter logFilter, LogFormatter logFormatter, WritableFile writableFile) {
        this.logFilter = logFilter;
        this.logFormatter = logFormatter;
        this.writableFile = writableFile;
    }

    public void log(Log log) throws IOException {
        List<LogAction> logs = getLogs(log);
        writeLogs(logs);
    }

    public List<LogAction> getLogs(Log log) {
        Collection<LogAction> logActions = log.getLogs();

        List<LogAction> logs = new ArrayList<>(logActions);
        logFilter.filter(logs);

        logs.sort(Comparator.comparingLong(LogAction::getTimeStamp));

        return logs;
    }

    private void writeLogs(List<LogAction> logs) throws IOException {
        OutputStreamWriter outputStreamWriter = getWriter();

        for (LogAction logAction : logs) {
            outputStreamWriter.write(logFormatter.format(logAction));
            outputStreamWriter.write("\n");
        }

        outputStreamWriter.close();
    }

    private OutputStreamWriter getWriter() throws FileNotFoundException {
        OutputStream outputStream = writableFile.getOutputSteam();
        return new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
    }
}
