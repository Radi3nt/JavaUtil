package fr.radi3nt.updater.git;

import fr.radi3nt.file.files.WritableFile;
import fr.radi3nt.updater.api.JavaChecker;
import fr.radi3nt.updater.api.JavaUpdater;
import fr.radi3nt.updater.api.UpdateManager;
import fr.radi3nt.updater.tag.update.GitUpdate;
import fr.radi3nt.updater.tag.versions.JavaVersion;
import fr.radi3nt.updater.url.SimpleVersionInterpreter;
import fr.radi3nt.updater.url.URLUpdater;

public class GitUpdaterManager implements UpdateManager {

    private final GitHubChecker javaChecker;
    private final WritableFile outputFile;

    private final JavaVersion current;

    public GitUpdaterManager(WritableFile outputFile, JavaVersion current, GitInfo gitInfo, String runFileName, String fetchVersionFileName, boolean installer) {
        this.outputFile = outputFile;
        this.current = current;
        javaChecker = new GitHubChecker(gitInfo, new SimpleVersionInterpreter<>(fetchedVersion -> new GitUpdate(gitInfo, runFileName, fetchedVersion), current.getVersionName(), installer), fetchVersionFileName);
    }

    @Override
    public JavaUpdater createUpdater() {

        if (!javaChecker.isFetched())
            javaChecker.fetch();

        return new URLUpdater(javaChecker.getFetchedUpdate(), outputFile);
    }

    @Override
    public JavaChecker getChecker() {
        return javaChecker;
    }

    @Override
    public JavaVersion getCurrent() {
        return current;
    }
}
