package fr.radi3nt.updater;

import fr.radi3nt.file.files.WritableFile;
import fr.radi3nt.updater.git.GitHubChecker;
import fr.radi3nt.updater.git.GitInfo;
import fr.radi3nt.updater.git.SimpleVersionInterpreter;
import fr.radi3nt.updater.git.URLUpdater;
import fr.radi3nt.updater.update.GitUpdate;
import fr.radi3nt.updater.versions.JavaVersion;

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
