package fr.radi3nt.loghelper.archiving.formatters;

import fr.radi3nt.loghelper.logs.actions.LogAction;

public class EmptyFormatter implements LogFormatter {

    @Override
    public String format(LogAction logAction) {
        return logAction.log();
    }

}
