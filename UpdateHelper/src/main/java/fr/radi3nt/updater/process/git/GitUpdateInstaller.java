package fr.radi3nt.updater.process.git;

import fr.radi3nt.file.impl.NioFile;
import fr.radi3nt.json.JsonObject;
import fr.radi3nt.json.WriterConfig;
import fr.radi3nt.timing.TimingUtil;
import fr.radi3nt.updater.components.git.GitAsset;
import fr.radi3nt.updater.components.git.GitRelease;
import fr.radi3nt.updater.download.ProgressTracker;
import fr.radi3nt.updater.util.JsonUtil;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

public class GitUpdateInstaller {

    private final GitFetcher gitFetcher;

    private final JsonObject previousVersionJson;
    private final NioFile versionFile;

    private final boolean forceInstall;
    private final String branch;

    private final Path directory;

    private final Predicate<GitAsset> doInstall;

    public GitUpdateInstaller(GitFetcher gitFetcher, Path directory, Predicate<GitAsset> doInstall, String branch, boolean forceInstall) {
        this.gitFetcher = gitFetcher;
        this.directory = directory;
        this.doInstall = doInstall;
        this.forceInstall = forceInstall;
        this.branch = branch;

        Path versionPath = directory.resolve("version.json");
        versionFile = new NioFile(versionPath);
        previousVersionJson = computeVersion(versionFile);
    }

    private static void writeVersionFile(NioFile versionFile, JsonObject versionJson) throws IOException {
        if (versionFile.isCreated()) {
            versionFile.delete();
        }

        versionFile.create();

        OutputStreamWriter outputStream = new OutputStreamWriter(versionFile.getOutputSteam());
        versionJson.writeTo(outputStream, WriterConfig.PRETTY_PRINT);
        outputStream.close();
    }

    private static JsonObject computeVersion(NioFile version) {

        if (!version.isCreated()) {
            return null;
        }

        return JsonUtil.getJsonValue(version).asObject();
    }

    private static GitRelease getLatestInBranch(String branch, GitRelease[] releases) {


        for (GitRelease release : releases) {
            if (release.getBranchName().equalsIgnoreCase(branch)) {
                return release;
            }
        }

        return null;
    }

    private static void fetchReleases(GitFetcher gitReleases) {
        try {
            gitReleases.fetch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean install(PrintStream error, PrintStream info) {
        fetchReleases(gitFetcher);

        GitRelease latestInBranch = getLatestInBranch(branch, gitFetcher.getReleases());

        if (latestInBranch==null) {
            error.println("Current branch has no release");
            error.println("Exiting program...");
            return false;
        }

        boolean updated = false;

        if (previousVersionJson==null || forceInstall || !latestInBranch.getBranchName().equalsIgnoreCase(previousVersionJson.getString("branch")) || !latestInBranch.getVersionTag().equalsIgnoreCase(previousVersionJson.getString("version"))) {
            updated = true;

            JsonObject versionJson = new JsonObject();
            versionJson.set("branch", latestInBranch.getBranchName());
            versionJson.set("version", latestInBranch.getVersionTag());

            if (forceInstall)
                System.out.println("Forcing installation of the latest release...");

            info.println("Newer version available: " + latestInBranch.getVersionTag() + " on " + latestInBranch.getBranchName());
            info.println("Downloading...");

            ExecutorService pool = Executors.newCachedThreadPool();
            Map<GitAsset, ProgressTracker> progressTrackerMap = new ConcurrentHashMap<>();
            for (GitAsset asset : latestInBranch.getAssets()) {
                if (doInstall.test(asset)) {

                    NioFile nioFile = new NioFile(directory.resolve(asset.getName()));

                    ProgressTracker progressTracker = new ProgressTracker();
                    progressTrackerMap.put(asset, progressTracker);

                    pool.submit(() -> {
                        try {
                            asset.download(nioFile, progressTracker);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }

            while (true) {
                for (Map.Entry<GitAsset, ProgressTracker> gitAssetProgressTrackerEntry : progressTrackerMap.entrySet()) {
                    long maxBytes = gitAssetProgressTrackerEntry.getKey().getByteAmount();
                    long currentBytes = gitAssetProgressTrackerEntry.getValue().getByteSize();
                    boolean finished = maxBytes==currentBytes;

                    if (!finished) {
                        BigDecimal bigDecimal = BigDecimal.valueOf(currentBytes).setScale(10000, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(maxBytes), RoundingMode.HALF_UP);
                        bigDecimal = bigDecimal.multiply(BigDecimal.valueOf(100));
                        bigDecimal = bigDecimal.setScale(0, RoundingMode.FLOOR);
                        info.println("Downloading " + gitAssetProgressTrackerEntry.getKey().getName() + ", progress " + currentBytes + "/" + maxBytes + " " + bigDecimal + "%");
                    } else {
                        info.println("Downloading of " + gitAssetProgressTrackerEntry.getKey().getName() + " is done");
                        progressTrackerMap.remove(gitAssetProgressTrackerEntry.getKey());
                    }
                }

                if (progressTrackerMap.isEmpty())
                    break;

                TimingUtil.waitMillis(1_000);
            }
            pool.shutdown();

            try {
                writeVersionFile(versionFile, versionJson);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            info.println("Current version is latest");
        }

        info.println("All files are up-to date");

        return updated;
    }


}
