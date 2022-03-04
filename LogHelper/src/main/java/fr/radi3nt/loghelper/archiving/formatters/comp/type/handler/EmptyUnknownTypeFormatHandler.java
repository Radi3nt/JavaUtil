package fr.radi3nt.loghelper.archiving.formatters.comp.type.handler;

import fr.radi3nt.loghelper.logs.actions.LogAction;

public class EmptyUnknownTypeFormatHandler implements UnknownTypeFormatHandler {

    @Override
    public String format(LogAction logAction) {
        return "";
    }
}
