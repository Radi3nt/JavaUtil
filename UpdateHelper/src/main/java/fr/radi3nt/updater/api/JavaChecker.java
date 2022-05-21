package fr.radi3nt.updater.api;

import fr.radi3nt.updater.tag.update.JavaUpdate;

public interface JavaChecker {

    void fetch();
    boolean isFetched();

    JavaUpdate getFetchedUpdate();
    boolean isUpToDate();

}
