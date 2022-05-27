package fr.radi3nt.updater.components.git;

import fr.radi3nt.file.files.WritableFile;
import fr.radi3nt.json.JsonObject;
import fr.radi3nt.json.JsonValue;
import fr.radi3nt.updater.api.Author;
import fr.radi3nt.updater.api.asset.DownloadableAsset;
import fr.radi3nt.updater.components.basic.BasicAsset;
import fr.radi3nt.updater.download.ProgressTracker;
import fr.radi3nt.updater.download.URLDownloader;
import fr.radi3nt.updater.util.TimeUtil;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;

public class GitAsset extends BasicAsset implements DownloadableAsset {

    private final String label;
    private final long byteAmount;

    private final String downloadURL;

    public GitAsset(int id, String name, LocalDateTime createdAt, LocalDateTime updatedAt, Author uploader, String label, long byteAmount, String downloadURL) {
        super(id, name, createdAt, updatedAt, uploader);
        this.label = label;
        this.byteAmount = byteAmount;
        this.downloadURL = downloadURL;
    }

    public static GitAsset from(JsonObject jsonObject) {

        int id = jsonObject.getInt("id", -1);

        String name = jsonObject.getString("name");

        JsonValue labelObject = jsonObject.get("label");
        String label = labelObject.isNull() ? null : labelObject.asString();

        String url = jsonObject.getString("browser_download_url");

        long size = jsonObject.getLong("size", -1);

        String createdAt = jsonObject.getString("created_at");
        String updatedAt = jsonObject.getString("updated_at");

        LocalDateTime createdAtTime = TimeUtil.convert(createdAt);
        LocalDateTime updatedAtTime = TimeUtil.convert(updatedAt);

        Author author = GitAuthor.from(jsonObject.get("uploader").asObject());

        return new GitAsset(id, name, createdAtTime, updatedAtTime, author, label, size, url);
    }

    public String getLabel() {
        return label;
    }

    public long getByteAmount() {
        return byteAmount;
    }

    @Override
    public void download(WritableFile path, ProgressTracker progressTracker) throws IOException {
        URLDownloader urlDownloader = new URLDownloader(new URL(downloadURL), path);
        urlDownloader.update(progressTracker);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GitAsset)) return false;
        if (!super.equals(o)) return false;
        GitAsset gitAsset = (GitAsset) o;
        return getByteAmount() == gitAsset.getByteAmount() && Objects.equals(getLabel(), gitAsset.getLabel()) && Objects.equals(downloadURL, gitAsset.downloadURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLabel(), getByteAmount(), downloadURL);
    }
}
