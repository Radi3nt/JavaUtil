package fr.radi3nt.lang.languages;

import fr.radi3nt.lang.exception.UnknownLanguageKeyException;

import java.io.File;

public interface Language {

    String getName();
    String getNameId();

    void setupLanguage(LanguageIndexer indexer);
    void update(LanguageIndexer languageIndexer);

    void addFile(File file);

    String getMessage(String id, String... placeHolders) throws UnknownLanguageKeyException;
    String getMessageOrError(String id, String... placeHolders);
    String getMessageOrDefault(String id, String defaultMessage, String... placeHolders);

}
