package fr.radi3nt.argp;

import fr.radi3nt.argp.exceptions.ArgumentException;
import fr.radi3nt.argp.parsing.ArgumentParser;
import fr.radi3nt.argp.parsing.result.ArgumentParseResult;
import fr.radi3nt.argp.parsing.repo.ArgumentRepository;
import fr.radi3nt.argp.parsing.result.ParserResult;
import fr.radi3nt.argp.parsing.result.ParserResultBuilder;
import fr.radi3nt.argp.parsing.result.ParserResultBuilderFactory;
import fr.radi3nt.argp.scheme.ArgScheme;

public class ArgParser {

    private final ArgScheme argScheme;
    private final ArgumentRepository argumentRepository;
    private final ParserResultBuilderFactory parserResultBuilderFactory;
    private ParserResult parserResult;

    public ArgParser(ArgScheme argScheme, ArgumentRepository argumentRepository, ParserResultBuilderFactory parserResultBuilderFactory) {
        this.argScheme = argScheme;
        this.argumentRepository = argumentRepository;
        this.parserResultBuilderFactory = parserResultBuilderFactory;
    }

    public void construct(String[] strings) throws ArgumentException {
        ParserResultBuilder parserResultBuilder = parserResultBuilderFactory.createBuilder();

        for (int i = 0; i < strings.length;) {
            String actualName = strings[i];
            ArgumentParser argumentParser = argumentRepository.getFromName(actualName);
            ArgumentParseResult parseResult = argumentParser.parse(strings, i);

            i = parseResult.getIndex();
            parserResultBuilder.addArgument(parseResult.getArgument());
        }

        parserResult = parserResultBuilder.build();
        argScheme.schemeValid(parserResult);
    }

    public ParserResult getParserResult() {
        return parserResult;
    }

}
