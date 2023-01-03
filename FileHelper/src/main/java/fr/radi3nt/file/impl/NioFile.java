package fr.radi3nt.file.impl;

import fr.radi3nt.file.FileAccess;
import fr.radi3nt.file.files.CompleteFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class NioFile implements CompleteFile {

    private final Path path;

    public NioFile(Path path) {
        this.path = path;
    }

    @Override
    public void create() throws IOException {
        Files.createDirectories(path.getParent());
        Files.createFile(path);
    }

    @Override
    public void delete() throws IOException {
        Files.deleteIfExists(path);
    }

    @Override
    public boolean isCreated() {
        return Files.exists(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(path.toFile().toPath());
    }

    @Override
    public OutputStream getOutputSteam() throws IOException {
        return Files.newOutputStream(path.toFile().toPath());
    }

    @Override
    public RandomAccessFile getRandomAccessFile(FileAccess fileAccess) throws FileNotFoundException {
        return new RandomAccessFile(path.toFile(), fileAccess.getStringId());
    }

    public Path getPath() {
        return path;
    }
}
