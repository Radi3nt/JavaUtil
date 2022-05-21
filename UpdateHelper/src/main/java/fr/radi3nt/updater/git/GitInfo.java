package fr.radi3nt.updater.git;

public class GitInfo {

    private final String profileName;
    private final String projectName;

    public GitInfo(String profileName, String projectName) {
        this.profileName = profileName;
        this.projectName = projectName;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getProjectName() {
        return projectName;
    }
}
