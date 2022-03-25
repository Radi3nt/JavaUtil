package fr.radi3nt.argp.arguments;

import fr.radi3nt.argp.Argument;

public class BooleanArgument implements Argument<Boolean> {

    private final String name;
    private final Boolean value;

    public BooleanArgument(String name, Boolean value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
