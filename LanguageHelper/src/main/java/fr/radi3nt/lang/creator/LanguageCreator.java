package fr.radi3nt.lang.creator;

import fr.radi3nt.lang.languages.Language;
import fr.radi3nt.lang.languages.LanguageIndexer;

import java.io.File;

public class LanguageCreator {

    private static LanguageFactory languageFactory;

    public static Language setup(File file, LanguageIndexer languageIndexer) {
        return languageFactory.setup(file, languageIndexer);
    }

    public static void setLanguageFactory(LanguageFactory languageFactory) {
        LanguageCreator.languageFactory = languageFactory;
    }
}
