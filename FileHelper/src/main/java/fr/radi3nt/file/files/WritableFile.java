package fr.radi3nt.file.files;

import java.io.IOException;
import java.io.OutputStream;

public interface WritableFile extends File {

    OutputStream getOutputSteam() throws IOException;

    void create() throws IOException;
    void delete() throws IOException;

}
