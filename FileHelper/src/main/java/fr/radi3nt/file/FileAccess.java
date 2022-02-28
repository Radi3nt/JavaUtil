package fr.radi3nt.file;

public enum FileAccess {

    READ("r"),
    WRITE("w"),
    READ_WRITE("rw"),
    ;


    private final String stringId;

    FileAccess(String stringId) {
        this.stringId = stringId;
    }

    public String getStringId() {
        return stringId;
    }
}
