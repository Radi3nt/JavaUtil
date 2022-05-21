package fr.radi3nt.updater.git.interpreter;

import fr.radi3nt.updater.update.JavaUpdate;

public interface VersionInterpreter {

    void interpret(String text);
    boolean isUpToDate();

    JavaUpdate getFetched();

}
