package fr.radi3nt.loghelper.archiving.formatters.comp.type;

import fr.radi3nt.loghelper.archiving.formatters.LogFormatter;
import fr.radi3nt.loghelper.archiving.formatters.comp.FormatComponent;
import fr.radi3nt.loghelper.archiving.formatters.comp.type.handler.UnknownTypeFormatHandler;
import fr.radi3nt.loghelper.logs.actions.LogAction;
import fr.radi3nt.loghelper.logs.actions.type.TypeLogAction;

public class TypeFormatterComponent implements FormatComponent {

    private final UnknownTypeFormatHandler unknownTypeFormatHandler;

    public TypeFormatterComponent(UnknownTypeFormatHandler unknownTypeFormatHandler) {
        this.unknownTypeFormatHandler = unknownTypeFormatHandler;
    }

    @Override
    public String format(LogAction logAction) {
        if (logAction instanceof TypeLogAction) {
            return "[" + ((TypeLogAction) logAction).getLogType().getName() + "] ";
        } else {
            return unknownTypeFormatHandler.format(logAction);
        }
    }

}
