package fr.radi3nt.argp.parsing.result;

import fr.radi3nt.argp.Argument;

public class ParserResult {

    private final Argument<?>[] arguments;

    public ParserResult(Argument<?>[] arguments) {
        this.arguments = arguments;
    }

    public Argument<?>[] getArguments() {
        return arguments;
    }
}
