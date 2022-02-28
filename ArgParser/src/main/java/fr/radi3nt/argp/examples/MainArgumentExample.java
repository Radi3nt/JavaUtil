package fr.radi3nt.argp.examples;

import fr.radi3nt.argp.ArgParser;
import fr.radi3nt.argp.Argument;
import fr.radi3nt.argp.exceptions.ArgumentException;
import fr.radi3nt.argp.parsing.parsers.StringArgumentParser;
import fr.radi3nt.argp.parsing.repo.MapArgumentRepository;
import fr.radi3nt.argp.parsing.result.parsers.ListParserResultBuilderFactory;
import fr.radi3nt.argp.scheme.AlwaysValidScheme;

public class MainArgumentExample {

    public static void main(String[] args) {

        MapArgumentRepository argumentRepository = new MapArgumentRepository("-");
        StringArgumentParser pseudoParser = new StringArgumentParser("-");
        argumentRepository.addArgumentParser("pseudo", pseudoParser);

        ArgParser argParser = new ArgParser(new AlwaysValidScheme(), argumentRepository, new ListParserResultBuilderFactory());

        try {
            argParser.construct(args);
        } catch (ArgumentException e) {
            e.printStackTrace();
        }

        System.out.println(pseudoParser.getStringArgument().getValue());

        for (Argument<?> argument : argParser.getParserResult().getArguments()) {
            System.out.println(argument.getName() + ": " + argument.getValue());
        }

    }

}
