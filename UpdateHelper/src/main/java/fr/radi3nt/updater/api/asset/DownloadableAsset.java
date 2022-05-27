package fr.radi3nt.updater.api.asset;

import fr.radi3nt.file.files.WritableFile;
import fr.radi3nt.updater.download.ProgressTracker;

import java.io.IOException;

public interface DownloadableAsset extends Asset {

    void download(WritableFile path, ProgressTracker progressTracker) throws IOException;

}
