package fr.radi3nt.updater.update;

public interface UpdateProvider<T extends JavaUpdate> {

    T create(String fetchedVersion);

}
