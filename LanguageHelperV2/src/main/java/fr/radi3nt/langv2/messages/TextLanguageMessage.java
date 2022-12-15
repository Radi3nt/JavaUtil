package fr.radi3nt.langv2.messages;

public class TextLanguageMessage implements LanguagePlaceHolderMessage {

    private String translation;

    public TextLanguageMessage(String translation) {
        this.translation = translation;
    }

    @Override
    public String getMessage() {
        return translation == null ? "No translation" : translation;
    }

    @Override
    public boolean isUnknown() {
        return translation == null;
    }

    @Override
    public void replace(String... strings) {
        if (translation != null)
            translation = String.format(translation, (Object[]) strings);
    }
}
