package fr.radi3nt.loghelper.logs.actions.type;

import fr.radi3nt.loghelper.logs.actions.TemplateLogAction;

public class TemplateTypeLogAction extends TemplateLogAction implements TypeLogAction {

    private final LogType logType;

    public TemplateTypeLogAction(LogType logType, String log, long timeStamp) {
        super(log, timeStamp);
        this.logType = logType;
    }

    @Override
    public LogType getLogType() {
        return logType;
    }
}
