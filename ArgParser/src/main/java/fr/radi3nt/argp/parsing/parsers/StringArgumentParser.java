package fr.radi3nt.argp.parsing.parsers;

import fr.radi3nt.argp.arguments.StringArgument;
import fr.radi3nt.argp.exceptions.ParsingException;
import fr.radi3nt.argp.parsing.ArgumentParser;
import fr.radi3nt.argp.parsing.result.ArgumentParseResult;

public class StringArgumentParser implements ArgumentParser {

    private final String prefix;
    private StringArgument stringArgument;

    public StringArgumentParser(String prefix) {
        this.prefix = prefix;
    }

    public StringArgumentParser() {
        this("");
    }

    @Override
    public ArgumentParseResult parse(String[] part, int index) throws ParsingException {
        try {
            return new ArgumentParseResult(stringArgument = new StringArgument(part[index].replaceFirst(prefix, ""), part[index+1]), index+2);
        } catch (Exception e) {
            throw new ParsingException(e);
        }
    }

    public boolean hasValue() {
        return stringArgument!=null;
    }

    public StringArgument getStringArgument() {
        return stringArgument;
    }

    public String getValue() {
        return stringArgument!=null ? stringArgument.getValue() : null;
    }
}
