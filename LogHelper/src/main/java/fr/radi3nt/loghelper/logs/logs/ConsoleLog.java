package fr.radi3nt.loghelper.logs.logs;

import fr.radi3nt.loghelper.logs.actions.LogAction;

public interface ConsoleLog extends WritableLog {

    void addSilentLog(LogAction logAction);

}
