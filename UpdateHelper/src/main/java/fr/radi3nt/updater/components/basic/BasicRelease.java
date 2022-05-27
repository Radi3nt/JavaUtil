package fr.radi3nt.updater.components.basic;

import fr.radi3nt.updater.api.Author;
import fr.radi3nt.updater.api.Release;
import fr.radi3nt.updater.api.asset.Asset;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class BasicRelease implements Release {

    private final int id;

    private final String name;
    private final String description;
    private final String versionTag;

    private final LocalDateTime createdAt;
    private final LocalDateTime publishedAt;

    private final Asset[] assets;
    private final Author author;

    public BasicRelease(int id, String name, String description, String versionTag, LocalDateTime createdAt, LocalDateTime publishedAt, Asset[] assets, Author author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.versionTag = versionTag;
        this.createdAt = createdAt;
        this.publishedAt = publishedAt;
        this.assets = assets;
        this.author = author;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getVersionTag() {
        return versionTag;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    @Override
    public Asset[] getAssets() {
        return assets;
    }

    @Override
    public Author getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicRelease)) return false;
        BasicRelease that = (BasicRelease) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getVersionTag(), that.getVersionTag()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getPublishedAt(), that.getPublishedAt()) && Arrays.equals(getAssets(), that.getAssets()) && Objects.equals(getAuthor(), that.getAuthor());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getDescription(), getVersionTag(), getCreatedAt(), getPublishedAt(), getAuthor());
        result = 31 * result + Arrays.hashCode(getAssets());
        return result;
    }
}
