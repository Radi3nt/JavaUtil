package fr.radi3nt.saving;

import fr.radi3nt.file.files.CompleteFile;

public interface SaveFileFactory {

    SaveFile getSaveFile(CompleteFile path);

}
