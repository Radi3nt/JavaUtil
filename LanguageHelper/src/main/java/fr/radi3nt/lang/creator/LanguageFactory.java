package fr.radi3nt.lang.creator;

import fr.radi3nt.lang.languages.Language;
import fr.radi3nt.lang.languages.LanguageIndexer;

import java.io.File;

public interface LanguageFactory {

    Language setup(File file, LanguageIndexer languageIndexer);

}
