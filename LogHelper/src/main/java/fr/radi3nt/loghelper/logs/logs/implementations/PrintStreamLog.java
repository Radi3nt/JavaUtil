package fr.radi3nt.loghelper.logs.logs.implementations;

import fr.radi3nt.loghelper.archiving.formatters.LogFormatter;
import fr.radi3nt.loghelper.logs.logs.ConsoleLog;
import fr.radi3nt.loghelper.logs.actions.LogAction;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class PrintStreamLog extends TemplateLog implements ConsoleLog {

    private final OutputStream outputStream;
    private final LogFormatter logFormatter;

    public PrintStreamLog(BufferedOutputStream outputStream, LogFormatter logFormatter) {
        this.outputStream = outputStream;
        this.logFormatter = logFormatter;
    }

    @Override
    public void addSilentLog(LogAction logAction) {
        super.addLogAction(logAction);
    }

    @Override
    public void addLogAction(LogAction logAction) {
        try {
            outputStream.write((logFormatter.format(logAction) + "\n").getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.addLogAction(logAction);
    }
}
