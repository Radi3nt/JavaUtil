package fr.radi3nt.loghelper.logs.logs;

import fr.radi3nt.loghelper.logs.actions.LogAction;

public interface WritableLog extends Log {

    void addLogAction(LogAction logAction);

}
