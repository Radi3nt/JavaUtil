package fr.radi3nt.file.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;

public final class RecursiveResourceVisitor {

    public static void consume(VisitorSupplier consumer, Class<?> cls, String root) throws URISyntaxException, IOException {
        URL location = cls.getProtectionDomain().getCodeSource().getLocation();
        if (location.getProtocol().equals("file")) {
            Path path = Paths.get(location.toURI());
            if (location.getPath().endsWith(".jar")) {
                try (FileSystem fs = FileSystems.newFileSystem(path, null)) {
                    walkTree(consumer, fs.getPath("/" + root));
                }
            } else {
                walkTree(consumer, path.resolve(root));
            }
        } else {
            throw new IllegalArgumentException("Not supported: " + location);
        }
    }

    private static void walkTree(VisitorSupplier visitor, Path src) throws IOException {
        Files.walkFileTree(src, visitor.get(src));
    }

    public interface VisitorSupplier {

        FileVisitor<Path> get(Path src);

    }
}