package fr.radi3nt.loghelper.examples;

import fr.radi3nt.file.impl.NioFile;
import fr.radi3nt.loghelper.archiving.LogArchiver;
import fr.radi3nt.loghelper.archiving.LogFilter;
import fr.radi3nt.loghelper.archiving.formatters.DefineFormatter;
import fr.radi3nt.loghelper.archiving.formatters.LogFormatter;
import fr.radi3nt.loghelper.archiving.formatters.comp.ComponentFormatter;
import fr.radi3nt.loghelper.archiving.formatters.comp.DateFormatterComponent;
import fr.radi3nt.loghelper.archiving.formatters.comp.type.TypeFormatterComponent;
import fr.radi3nt.loghelper.archiving.formatters.comp.type.handler.EmptyUnknownTypeFormatHandler;
import fr.radi3nt.loghelper.logs.actions.TemplateLogAction;
import fr.radi3nt.loghelper.logs.actions.type.EnumLogType;
import fr.radi3nt.loghelper.logs.actions.type.InfoLogAction;
import fr.radi3nt.loghelper.logs.actions.type.TemplateTypeLogAction;
import fr.radi3nt.loghelper.logs.logs.WritableLog;
import fr.radi3nt.loghelper.logs.logs.implementations.PrintStreamLog;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

public class MainLogExample {

    private static final LogFormatter logFormatter = new ComponentFormatter(new DefineFormatter(),
            new DateFormatterComponent(DateTimeFormatter.ofPattern("HH:mm:ss")),
            new TypeFormatterComponent(new EmptyUnknownTypeFormatHandler()));

    public static void main(String[] args) {
        WritableLog writableLog = new PrintStreamLog(new BufferedOutputStream(System.out), logFormatter);

        long started = System.currentTimeMillis();
        for (int i = 0; i < 1_000; i++) {
            writableLog.addLogAction(new InfoLogAction("Printing thingy"));
        }
        long stopped = System.currentTimeMillis();
        System.out.println((stopped-started)/1_000d);

        /*
        writableLog.addLogAction(new TemplateTypeLogAction(EnumLogType.WARN, "Some Random Warn", System.currentTimeMillis()));
        writableLog.addLogAction(new TemplateLogAction("Using some raw log action", System.currentTimeMillis()) {});
        writableLog.addLogAction(new InfoLogAction("Finished program"));

        writeLog(writableLog);

         */
    }

    private static void writeLog(WritableLog writableLog) {
        NioFile nioFile = new NioFile(Paths.get("logs/", "myLog.log"));
        createFileIfNeeded(nioFile);

        LogArchiver logArchiver = new LogArchiver(new LogFilter(logAction -> true), MainLogExample.logFormatter, nioFile);
        try {
            logArchiver.log(writableLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFileIfNeeded(NioFile nioFile) {
        if (!nioFile.isCreated()) {
            try {
                nioFile.create();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
