package fr.radi3nt.file.files;

import fr.radi3nt.file.FileAccess;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public interface CompleteFile extends WritableFile, ReadableFile {

    RandomAccessFile getRandomAccessFile(FileAccess fileAccess) throws FileNotFoundException;

}
