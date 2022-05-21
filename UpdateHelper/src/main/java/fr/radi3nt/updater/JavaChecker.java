package fr.radi3nt.updater;

import fr.radi3nt.updater.update.JavaUpdate;

public interface JavaChecker {

    void fetch();
    boolean isFetched();

    JavaUpdate getFetchedUpdate();
    boolean isUpToDate();

}
