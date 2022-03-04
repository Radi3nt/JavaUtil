package fr.radi3nt.loghelper.logs.logs;

import fr.radi3nt.loghelper.logs.actions.LogAction;

import java.util.Collection;

public interface Log {

    Collection<LogAction> getLogs();

}
