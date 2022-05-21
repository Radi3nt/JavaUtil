package fr.radi3nt.updater.tag.versions;

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
