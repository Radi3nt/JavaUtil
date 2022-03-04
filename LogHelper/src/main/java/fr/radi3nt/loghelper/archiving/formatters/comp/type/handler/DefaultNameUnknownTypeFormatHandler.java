package fr.radi3nt.loghelper.archiving.formatters.comp.type.handler;

import fr.radi3nt.loghelper.logs.actions.LogAction;

public class DefaultNameUnknownTypeFormatHandler implements UnknownTypeFormatHandler {

    private final String name;

    public DefaultNameUnknownTypeFormatHandler(String name) {
        this.name = name;
    }

    @Override
    public String format(LogAction logAction) {
        return "[" + name + "] ";
    }
}
