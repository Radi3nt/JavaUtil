package fr.radi3nt.argp.parsing.repo;

import fr.radi3nt.argp.exceptions.ArgumentNameNotFoundException;
import fr.radi3nt.argp.parsing.ArgumentParser;

public interface ArgumentRepository {

    ArgumentParser getFromName(String name) throws ArgumentNameNotFoundException;

}
