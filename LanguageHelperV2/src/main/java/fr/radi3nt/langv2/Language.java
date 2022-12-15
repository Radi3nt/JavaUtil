package fr.radi3nt.langv2;

import fr.radi3nt.langv2.keys.LanguageKey;
import fr.radi3nt.langv2.messages.LanguageMessage;

public interface Language {

    String getLanguageId();

    LanguageMessage getMessage(LanguageKey key);

}
