package fr.radi3nt.saving;

import fr.radi3nt.saving.exceptions.FileOperationException;

public interface SaveFile {

    void save() throws FileOperationException;
    void load() throws FileOperationException;
    //todo void unload() throws FileOperationException;

    boolean isLoaded();
    boolean isFileUpToDate();

}
