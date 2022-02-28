package fr.radi3nt.saving.impl;

import fr.radi3nt.file.files.CompleteFile;
import fr.radi3nt.saving.SaveFileFactory;

public class JsonSaveFileFactory implements SaveFileFactory {

    private static final JsonSaveFileFactory INSTANCE = new JsonSaveFileFactory();

    @Override
    public JsonSaveFile getSaveFile(CompleteFile path) {
        return new JsonSaveFile(path);
    }

    public static JsonSaveFileFactory getInstance() {
        return INSTANCE;
    }
}
