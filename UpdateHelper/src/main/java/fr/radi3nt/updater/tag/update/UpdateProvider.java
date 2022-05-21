package fr.radi3nt.updater.tag.update;

public interface UpdateProvider<T extends JavaUpdate> {

    T create(String fetchedVersion);

}
