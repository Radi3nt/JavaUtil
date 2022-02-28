package fr.radi3nt.lang.languages;

import java.util.ArrayList;
import java.util.List;

public final class LanguageIndexer {

    private final List<Language> languages = new ArrayList<>();

    public void addLanguage(Language language) {
        languages.add(language);
    }

    public void removeLanguage(Language language) {
        languages.add(language);
    }

    public void updateLanguage(Language language) {
        languages.add(language);
    }

    public List<Language> getLanguages() {
        return languages;
    }
}
