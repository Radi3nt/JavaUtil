package fr.radi3nt.updater.components.basic;

import fr.radi3nt.updater.api.Author;
import fr.radi3nt.updater.api.asset.Asset;

import java.time.LocalDateTime;
import java.util.Objects;

public class BasicAsset implements Asset {

    private final int id;
    private final String name;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private final Author uploader;

    public BasicAsset(int id, String name, LocalDateTime createdAt, LocalDateTime updatedAt, Author uploader) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.uploader = uploader;
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public Author getUploader() {
        return uploader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicAsset)) return false;
        BasicAsset that = (BasicAsset) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getUpdatedAt(), that.getUpdatedAt()) && Objects.equals(getUploader(), that.getUploader());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCreatedAt(), getUpdatedAt(), getUploader());
    }
}
