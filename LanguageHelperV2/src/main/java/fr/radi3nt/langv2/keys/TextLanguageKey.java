package fr.radi3nt.langv2.keys;

import java.util.Objects;

public class TextLanguageKey implements LanguageKey {

    protected final String key;

    protected TextLanguageKey(String key) {
        this.key = key;
    }

    public static TextLanguageKey from(String text) {
        return new TextLanguageKey(text);
    }

    public String getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextLanguageKey)) return false;
        TextLanguageKey that = (TextLanguageKey) o;
        return Objects.equals(getKey(), that.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
    }
}
