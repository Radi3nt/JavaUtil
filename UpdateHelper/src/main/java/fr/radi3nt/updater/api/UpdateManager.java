package fr.radi3nt.updater.api;

import fr.radi3nt.updater.tag.versions.JavaVersion;

public interface UpdateManager {

    JavaUpdater createUpdater();
    JavaChecker getChecker();
    JavaVersion getCurrent();

}
