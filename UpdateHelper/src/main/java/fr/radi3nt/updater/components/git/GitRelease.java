package fr.radi3nt.updater.components.git;

import fr.radi3nt.json.JsonArray;
import fr.radi3nt.json.JsonObject;
import fr.radi3nt.json.JsonValue;
import fr.radi3nt.updater.api.Author;
import fr.radi3nt.updater.api.asset.Asset;
import fr.radi3nt.updater.components.basic.BasicRelease;
import fr.radi3nt.updater.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class GitRelease extends BasicRelease {

    private final String branchName;
    private final boolean draft;
    private final boolean preRelease;

    public GitRelease(int id, String name, String description, String versionTag, LocalDateTime createdAt, LocalDateTime publishedAt, Asset[] assets, Author author, String branchName, boolean draft, boolean preRelease) {
        super(id, name, description, versionTag, createdAt, publishedAt, assets, author);
        this.branchName = branchName;
        this.draft = draft;
        this.preRelease = preRelease;
    }


    public static GitRelease from(JsonObject release) {

        int id = release.getInt("id", -1);

        JsonValue nameObject = release.get("name");
        String name = nameObject.isNull() ? null : nameObject.asString();

        JsonValue bodyObject = release.get("body");
        String body = bodyObject.isNull() ? null : bodyObject.asString();

        String tagName = release.getString("tag_name");

        String branchName = release.getString("target_commitish");

        boolean draft = release.getBoolean("draft", false);
        boolean prerelease = release.getBoolean("prerelease", false);

        String createdAt = release.getString("created_at");

        JsonValue publishedAtObject = release.get("published_at");
        String publishedAt = publishedAtObject.isNull() ? null : publishedAtObject.asString();

        LocalDateTime createdAtTime = TimeUtil.convert(createdAt);
        LocalDateTime publishedAtTime = TimeUtil.convert(publishedAt);

        JsonArray assetsObjects = release.get("assets").asArray();
        GitAsset[] assets = new GitAsset[assetsObjects.size()];

        List<JsonValue> values = assetsObjects.values();
        for (int i = 0; i < values.size(); i++) {
            JsonValue value = values.get(i);
            assets[i] = GitAsset.from(value.asObject());
        }

        Author author = GitAuthor.from(release.get("author").asObject());

        return new GitRelease(id, name, body, tagName, createdAtTime, publishedAtTime, assets, author, branchName, draft, prerelease);
    }

    public String getBranchName() {
        return branchName;
    }

    public boolean isDraft() {
        return draft;
    }

    public boolean isPreRelease() {
        return preRelease;
    }

    @Override
    public GitAsset[] getAssets() {
        return (GitAsset[]) super.getAssets();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GitRelease)) return false;
        if (!super.equals(o)) return false;
        GitRelease that = (GitRelease) o;
        return isDraft() == that.isDraft() && isPreRelease() == that.isPreRelease() && Objects.equals(getBranchName(), that.getBranchName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBranchName(), isDraft(), isPreRelease());
    }
}
