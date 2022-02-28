package fr.radi3nt.argp.parsing.result.parsers;

import fr.radi3nt.argp.parsing.result.ParserResultBuilder;
import fr.radi3nt.argp.parsing.result.ParserResultBuilderFactory;

public class ListParserResultBuilderFactory implements ParserResultBuilderFactory {

    @Override
    public ParserResultBuilder createBuilder() {
        return new ListParserResultBuilder();
    }

}
