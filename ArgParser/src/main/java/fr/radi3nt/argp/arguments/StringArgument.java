package fr.radi3nt.argp.arguments;

import fr.radi3nt.argp.Argument;

public class StringArgument implements Argument<String> {

    private final String name;
    private final String value;

    public StringArgument(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }
}
