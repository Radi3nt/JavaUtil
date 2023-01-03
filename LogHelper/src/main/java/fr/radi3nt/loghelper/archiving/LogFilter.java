package fr.radi3nt.loghelper.archiving;

import fr.radi3nt.loghelper.logs.actions.LogAction;

import java.util.Collection;
import java.util.function.Predicate;

public class LogFilter {

    private final Predicate<LogAction> loggedActionPredicate;

    public LogFilter(Predicate<LogAction> loggedActionPredicate) {
        this.loggedActionPredicate = loggedActionPredicate;
    }

    public boolean filter(LogAction logAction) {
        return loggedActionPredicate.test(logAction);
    }

    public void filter(Collection<LogAction> logActions) {
        logActions.removeIf(logAction -> !loggedActionPredicate.test(logAction));
    }

}
