package fr.radi3nt.json;

public class JsonParseException extends RuntimeException {
    private final CharLocation location;

    JsonParseException(final String message, final CharLocation location) {
        super(message + " at " + location);
        this.location = location;
    }

    public CharLocation getLocation() {
        return this.location;
    }

    @Deprecated
    public int getOffset() {
        return this.location.offset;
    }

    @Deprecated
    public int getLine() {
        return this.location.line;
    }

    @Deprecated
    public int getColumn() {
        return this.location.column;
    }
}
