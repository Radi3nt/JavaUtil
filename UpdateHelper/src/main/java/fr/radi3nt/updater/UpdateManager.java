package fr.radi3nt.updater;

import fr.radi3nt.updater.versions.JavaVersion;

public interface UpdateManager {

    JavaUpdater createUpdater();
    JavaChecker getChecker();
    JavaVersion getCurrent();

}
