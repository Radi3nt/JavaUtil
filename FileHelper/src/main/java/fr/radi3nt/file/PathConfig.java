package fr.radi3nt.file;

import java.nio.file.Path;

public class PathConfig {

    private final Path path;

    public PathConfig(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public PathConfig resolve(String toResolve) {
        return new PathConfig(path.resolve(toResolve));
    }
}
