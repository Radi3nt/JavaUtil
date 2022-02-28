package fr.radi3nt.argp.exceptions;

public class ArgumentNameNotFoundException extends ArgumentException {

    public ArgumentNameNotFoundException(String argumentName) {
        super("Argument \"" + argumentName + "\" is not recognized");
    }

}
