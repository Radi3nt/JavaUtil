package fr.radi3nt.file.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Installer extends SimpleFileVisitor<Path> {

    public static void installResources(Path dst, Class<?> cls, String root) throws URISyntaxException, IOException {
        URL location = cls.getProtectionDomain().getCodeSource().getLocation();
        if (location.getProtocol().equals("file")) {
            Path path = Paths.get(location.toURI());
            if (location.getPath().endsWith(".jar")) {
                try (FileSystem fs = FileSystems.newFileSystem(path, null)) {
                    installResources(dst, fs.getPath("/" + root));
                }
            } else {
                installResources(dst, path.resolve(root));
            }
        } else {
            throw new IllegalArgumentException("Not supported: " + location);
        }
    }

    private static void installResources(Path dst, Path src) throws IOException {
        Files.walkFileTree(src, new Installer(dst, src));
    }

    private final Path target, source;

    private Installer(Path dst, Path src) {
        target = dst;
        source = src;
    }

    private Path resolve(Path path) {
        return target.resolve(source.relativize(path).toString());
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path dst = resolve(dir);
        Files.createDirectories(dst);
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path dst = resolve(file);
        Files.copy(Files.newInputStream(file), dst, StandardCopyOption.REPLACE_EXISTING);
        return super.visitFile(file, attrs);
    }
}