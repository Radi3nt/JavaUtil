package fr.radi3nt.argp.parsing.parsers;

import fr.radi3nt.argp.arguments.BooleanArgument;
import fr.radi3nt.argp.exceptions.ParsingException;
import fr.radi3nt.argp.parsing.ArgumentParser;
import fr.radi3nt.argp.parsing.result.ArgumentParseResult;

public class BooleanArgumentParser implements ArgumentParser {

    private final String prefix;
    private BooleanArgument argument;

    public BooleanArgumentParser(String prefix) {
        this.prefix = prefix;
    }

    public BooleanArgumentParser() {
        this("");
    }

    @Override
    public ArgumentParseResult parse(String[] part, int index) throws ParsingException {
        try {
            return new ArgumentParseResult(argument = new BooleanArgument(part[index].replaceFirst(prefix, ""), Boolean.parseBoolean(part[index + 1].toLowerCase().trim())), index + 2);
        } catch (Exception e) {
            throw new ParsingException(e);
        }
    }

    public boolean hasValue() {
        return argument != null;
    }

    public BooleanArgument getArgument() {
        return argument;
    }

    public Boolean getValue() {
        return argument != null ? argument.getValue() : null;
    }
}
