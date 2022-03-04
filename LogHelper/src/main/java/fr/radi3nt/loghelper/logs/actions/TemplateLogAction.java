package fr.radi3nt.loghelper.logs.actions;

public abstract class TemplateLogAction implements LogAction {

    protected String log;
    protected final long timeStamp;

    public TemplateLogAction(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public TemplateLogAction(String log, long timeStamp) {
        this(timeStamp);

        this.log = log;
    }

    @Override
    public String log() {
        return log;
    }

    @Override
    public long getTimeStamp() {
        return timeStamp;
    }
}
