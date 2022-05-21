package fr.radi3nt.updater.versions;

public class TaggedVersion implements JavaVersion {

    private final String versionName;

    public TaggedVersion(String versionName) {
        this.versionName = versionName;
    }

    @Override
    public String getVersionName() {
        return versionName;
    }
}
