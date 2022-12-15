package fr.radi3nt.langv2.messages;

public interface LanguagePlaceHolderMessage extends LanguageMessage {

    void replace(String... strings);

}
