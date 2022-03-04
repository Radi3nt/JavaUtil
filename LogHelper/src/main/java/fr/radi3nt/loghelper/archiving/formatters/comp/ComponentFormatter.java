package fr.radi3nt.loghelper.archiving.formatters.comp;

import fr.radi3nt.loghelper.archiving.formatters.LogFormatter;
import fr.radi3nt.loghelper.logs.actions.LogAction;

import java.util.Arrays;
import java.util.Collection;

public class ComponentFormatter implements LogFormatter {

    private final Collection<FormatComponent> formatComponents;
    private final LogFormatter logFormatter;

    public ComponentFormatter(Collection<FormatComponent> formatComponents, LogFormatter logFormatter) {
        this.logFormatter = logFormatter;
        this.formatComponents = formatComponents;
    }

    public ComponentFormatter(LogFormatter logFormatter, FormatComponent... formatComponents) {
        this(Arrays.asList(formatComponents), logFormatter);
    }

    @Override
    public String format(LogAction logAction) {
        StringBuilder stringBuilder = new StringBuilder();
        for (FormatComponent formatComponent : formatComponents) {
            stringBuilder.append(formatComponent.format(logAction));
        }

        stringBuilder.append(logFormatter.format(logAction));

        return stringBuilder.toString();
    }
}
