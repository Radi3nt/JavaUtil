package fr.radi3nt.file.files;

import java.io.FileNotFoundException;
import java.io.OutputStream;

public interface WritableFile extends File {

    OutputStream getOutputSteam() throws FileNotFoundException;

}
