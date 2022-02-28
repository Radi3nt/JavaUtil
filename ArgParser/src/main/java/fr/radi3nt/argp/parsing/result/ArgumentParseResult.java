package fr.radi3nt.argp.parsing.result;

import fr.radi3nt.argp.Argument;

public class ArgumentParseResult {

    private final Argument<?> object;
    private final int index;

    public ArgumentParseResult(Argument<?> object, int index) {
        this.object = object;
        this.index = index;
    }

    public Argument<?> getArgument() {
        return object;
    }

    public int getIndex() {
        return index;
    }
}
