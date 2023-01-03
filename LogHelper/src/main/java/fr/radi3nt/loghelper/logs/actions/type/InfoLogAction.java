package fr.radi3nt.loghelper.logs.actions.type;

public class InfoLogAction extends TemplateTypeLogAction {

    public InfoLogAction(String log, long timeStamp) {
        super(EnumLogType.INFO, log, timeStamp);
    }

    public InfoLogAction(String log) {
        this(log, System.currentTimeMillis());
    }

}