package fr.radi3nt.loghelper.logs.actions.type;

public enum EnumLogType implements LogType {

    INFO("INFO"),
    WARN("WARN"),
    ERROR("ERROR"),
    ;

    private final String name;

    EnumLogType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
