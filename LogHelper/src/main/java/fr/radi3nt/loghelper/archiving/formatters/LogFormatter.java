package fr.radi3nt.loghelper.archiving.formatters;

import fr.radi3nt.loghelper.logs.actions.LogAction;

public interface LogFormatter {

    String format(LogAction logAction);

}
