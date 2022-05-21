package fr.radi3nt.updater.git;

import fr.radi3nt.updater.JavaChecker;
import fr.radi3nt.updater.update.URLUpdate;
import fr.radi3nt.updater.util.StreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class GitHubChecker implements JavaChecker {

    private final GitInfo gitInfo;
    private final SimpleVersionInterpreter<URLUpdate> versionInterpreter;
    private final String versionFileName;

    private boolean wasVersionChecked = false;

    public GitHubChecker(GitInfo gitInfo, SimpleVersionInterpreter<URLUpdate> versionInterpreter, String versionFileName) {
        this.gitInfo = gitInfo;
        this.versionInterpreter = versionInterpreter;
        this.versionFileName = versionFileName;
    }

    public void checkStatus() {
        URL url;
        try {
            url = new URL("https://raw.githubusercontent.com/" + gitInfo.getProfileName() + "/" + gitInfo.getProjectName() + "/master/" + versionFileName);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        String updateFileText;
        try {
            InputStream inputStream = url.openConnection().getInputStream();
            updateFileText = StreamUtil.read(inputStream);

            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        versionInterpreter.interpret(updateFileText);
    }

    private void updateStatus() {
        checkStatus();
        wasVersionChecked = true;
    }

    @Override
    public void fetch() {
        if (!wasVersionChecked)
            updateStatus();
    }

    @Override
    public boolean isFetched() {
        return wasVersionChecked;
    }

    @Override
    public URLUpdate getFetchedUpdate() {
        return versionInterpreter.getFetched();
    }

    @Override
    public boolean isUpToDate() {
        return versionInterpreter.isUpToDate();
    }
}
