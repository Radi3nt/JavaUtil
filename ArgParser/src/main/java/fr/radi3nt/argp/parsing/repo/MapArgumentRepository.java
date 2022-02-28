package fr.radi3nt.argp.parsing.repo;

import fr.radi3nt.argp.exceptions.ArgumentNameNotFoundException;
import fr.radi3nt.argp.parsing.ArgumentParser;

import java.util.HashMap;
import java.util.Map;

public class MapArgumentRepository implements ArgumentRepository {

    private final String prefix;
    private final Map<String, ArgumentParser> parserMap;

    public MapArgumentRepository(String prefix, Map<String, ArgumentParser> parserMap) {
        this.prefix = prefix;
        this.parserMap = parserMap;
    }

    public MapArgumentRepository(String prefix) {
        this(prefix, new HashMap<>());
    }

    public void addArgumentParser(String name, ArgumentParser argumentParser) {
        parserMap.put(name, argumentParser);
    }

    @Override
    public ArgumentParser getFromName(String name) throws ArgumentNameNotFoundException {
        ArgumentParser argumentParser = parserMap.get(name.replaceFirst(prefix, ""));

        if (argumentParser==null)
            throw new ArgumentNameNotFoundException(name);

        return argumentParser;
    }

}
