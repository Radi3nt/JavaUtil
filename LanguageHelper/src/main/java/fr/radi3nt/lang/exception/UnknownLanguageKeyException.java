package fr.radi3nt.lang.exception;

import fr.radi3nt.lang.languages.Language;

public class UnknownLanguageKeyException extends Exception {

    private final Language language;

    public UnknownLanguageKeyException(Language language, String key) {
        super(key);
        this.language = language;
    }

    public void handle() {
        String text = "No translation in language " + language.getName() + " for " + getMessage();
        System.err.println(text);
    }

    public Language getLanguage() {
        return language;
    }
}
