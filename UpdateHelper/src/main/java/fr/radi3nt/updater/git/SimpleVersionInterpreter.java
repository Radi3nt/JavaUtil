package fr.radi3nt.updater.git;

import fr.radi3nt.updater.git.interpreter.VersionInterpreter;
import fr.radi3nt.updater.update.JavaUpdate;
import fr.radi3nt.updater.update.UpdateProvider;

public class SimpleVersionInterpreter<T extends JavaUpdate> implements VersionInterpreter {

    private final String currentVersion;
    private final UpdateProvider<T> updateProvider;
    private final boolean installer;

    private String fetchedVersion;
    private T fetched;

    public SimpleVersionInterpreter(UpdateProvider<T> updateProvider, String currentVersion, boolean installer) {
        this.updateProvider = updateProvider;
        this.currentVersion = currentVersion;
        this.installer = installer;
    }

    @Override
    public void interpret(String text) {
        String[] lines = text.split("\n");

        fetchedVersion = lines[installer ? 1 : 0];

        fetched = updateProvider.create(fetchedVersion);
    }

    @Override
    public boolean isUpToDate() {
        return currentVersion.equalsIgnoreCase(fetchedVersion);
    }

    @Override
    public T getFetched() {
        return fetched;
    }
}
