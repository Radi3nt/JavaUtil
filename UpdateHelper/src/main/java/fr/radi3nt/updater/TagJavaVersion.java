package fr.radi3nt.updater;

import fr.radi3nt.updater.versions.JavaVersion;

public class TagJavaVersion implements JavaVersion {

    private final String version;

    public TagJavaVersion(String version) {
        this.version = version;
    }

    @Override
    public String getVersionName() {
        return version;
    }
}
