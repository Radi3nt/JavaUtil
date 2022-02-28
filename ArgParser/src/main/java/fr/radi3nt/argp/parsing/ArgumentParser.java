package fr.radi3nt.argp.parsing;

import fr.radi3nt.argp.parsing.result.ArgumentParseResult;
import fr.radi3nt.argp.exceptions.ParsingException;

public interface ArgumentParser {

    ArgumentParseResult parse(String[] part, int index) throws ParsingException;

}
