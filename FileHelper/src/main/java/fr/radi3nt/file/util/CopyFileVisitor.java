package fr.radi3nt.file.util;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFileVisitor extends SimpleFileVisitor<Path> {

    private final Path target;
    private final Path source;

    public CopyFileVisitor(Path target, Path source) {
        this.target = target;
        this.source = source;
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

    private Path resolve(Path path) {
        return target.resolve(source.relativize(path).toString());
    }

}
