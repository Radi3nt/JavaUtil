package fr.radi3nt.updater.api;

import fr.radi3nt.updater.tag.update.JavaUpdate;

public interface VersionInterpreter {

    void interpret(String text);
    boolean isUpToDate();

    JavaUpdate getFetched();

}
