package fr.radi3nt.argp.scheme;

import fr.radi3nt.argp.parsing.result.ParserResult;
import fr.radi3nt.argp.exceptions.ArgumentException;

/**
 * Argument scheme precise the validity of a chain of arguments
 */
public interface ArgScheme {

    void schemeValid(ParserResult parserResult) throws ArgumentException;

}
