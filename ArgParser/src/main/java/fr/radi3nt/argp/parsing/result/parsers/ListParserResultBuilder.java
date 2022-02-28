package fr.radi3nt.argp.parsing.result.parsers;

import fr.radi3nt.argp.Argument;
import fr.radi3nt.argp.parsing.result.ParserResult;
import fr.radi3nt.argp.parsing.result.ParserResultBuilder;

import java.util.ArrayList;
import java.util.List;

public class ListParserResultBuilder implements ParserResultBuilder {

    private final List<Argument<?>> arguments;

    public ListParserResultBuilder(List<Argument<?>> arguments) {
        this.arguments = arguments;
    }

    public ListParserResultBuilder() {
        this(new ArrayList<>());
    }

    @Override
    public void addArgument(Argument<?> argument) {
        arguments.add(argument);
    }

    @Override
    public ParserResult build() {
        return new ParserResult(arguments.toArray(new Argument[0]));
    }
}
