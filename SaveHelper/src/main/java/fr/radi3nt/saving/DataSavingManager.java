package fr.radi3nt.saving;

public interface DataSavingManager<T extends SaveData> {

    T load() throws Exception;
    void save(T saveData) throws Exception;

}
