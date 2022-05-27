package fr.radi3nt.updater.api;

import fr.radi3nt.updater.api.asset.Asset;

import java.time.LocalDateTime;

public interface Release {

    String getName();
    String getDescription();

    String getVersionTag();
    int getId();

    LocalDateTime getCreatedAt();
    LocalDateTime getPublishedAt();

    Asset[] getAssets();

    Author getAuthor();

}
