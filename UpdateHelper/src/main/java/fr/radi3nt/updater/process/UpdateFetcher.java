package fr.radi3nt.updater.process;

import fr.radi3nt.updater.api.Release;

import java.io.IOException;

public interface UpdateFetcher<T extends Release> {

    void fetch() throws IOException;
    T[] getReleases();

}
