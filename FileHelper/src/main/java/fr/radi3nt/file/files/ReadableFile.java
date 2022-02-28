package fr.radi3nt.file.files;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface ReadableFile extends File {

    InputStream getInputStream() throws FileNotFoundException;

}
