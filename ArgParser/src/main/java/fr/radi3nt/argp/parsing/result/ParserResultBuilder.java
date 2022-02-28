package fr.radi3nt.argp.parsing.result;

import fr.radi3nt.argp.Argument;

public interface ParserResultBuilder {

    void addArgument(Argument<?> argument);
    ParserResult build();

}
