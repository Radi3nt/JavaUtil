package fr.radi3nt.file.files;

import java.io.IOException;
import java.io.InputStream;

public interface ReadableFile extends File {

    InputStream getInputStream() throws IOException;

}
