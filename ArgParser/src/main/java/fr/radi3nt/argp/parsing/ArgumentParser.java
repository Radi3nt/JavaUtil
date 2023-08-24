package fr.radi3nt.argp.parsing;

import fr.radi3nt.argp.exceptions.ParsingException;
import fr.radi3nt.argp.parsing.result.ArgumentParseResult;

public interface ArgumentParser {

    ArgumentParseResult parse(String[] part, int index) throws ParsingException;

}
