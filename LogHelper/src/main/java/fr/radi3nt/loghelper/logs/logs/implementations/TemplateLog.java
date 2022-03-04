package fr.radi3nt.loghelper.logs.logs.implementations;

import fr.radi3nt.loghelper.logs.actions.LogAction;
import fr.radi3nt.loghelper.logs.logs.WritableLog;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TemplateLog implements WritableLog {

    protected final Set<LogAction> logActions = Collections.newSetFromMap(new ConcurrentHashMap<>());

    @Override
    public void addLogAction(LogAction logAction) {
        logActions.add(logAction);
    }

    @Override
    public Collection<LogAction> getLogs() {
        return logActions;
    }
}
