package fr.radi3nt.updater.api.asset;

import fr.radi3nt.updater.api.Author;

import java.time.LocalDateTime;

public interface Asset {

    int getId();
    String getName();

    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    Author getUploader();

}
