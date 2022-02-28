package fr.radi3nt.file.impl;

import fr.radi3nt.file.files.CompleteFile;
import fr.radi3nt.file.FileAccess;

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
    public boolean isCreated() {
        return Files.exists(path);
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(path.toFile());
    }

    @Override
    public OutputStream getOutputSteam() throws FileNotFoundException {
        return new FileOutputStream(path.toFile());
    }

    @Override
    public RandomAccessFile getRandomAccessFile(FileAccess fileAccess) throws FileNotFoundException {
        return new RandomAccessFile(path.toFile(), fileAccess.getStringId());
    }
}
