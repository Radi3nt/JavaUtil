package fr.radi3nt.updater.tag.update;

import fr.radi3nt.updater.git.GitInfo;

import java.net.MalformedURLException;
import java.net.URL;

public class GitUpdate implements URLUpdate {

    private final GitInfo gitInfo;
    private final String fetchedFileName;
    private final String updateTag;

    public GitUpdate(GitInfo gitInfo, String fetchedFileName, String updateTag) {
        this.gitInfo = gitInfo;
        this.fetchedFileName = fetchedFileName;
        this.updateTag = updateTag;
    }

    @Override
    public String getTag() {
        return updateTag;
    }

    @Override
    public URL createURL() throws MalformedURLException {
        return new URL("https://github.com/" + gitInfo.getProfileName() +"/" + gitInfo.getProjectName() + "/releases/download/" + updateTag + "/" + fetchedFileName);
    }
}
