package fr.radi3nt.json;

import java.io.Writer;

public abstract class WriterConfig {

    public static final WriterConfig MINIMAL = new WriterConfig() {
        @Override
        JsonWriter createWriter(final Writer writer) {
            return new JsonWriter(writer);
        }
    };
    public static final WriterConfig PRETTY_PRINT = PrettyPrint.indentWithSpaces(2);

    abstract JsonWriter createWriter(final Writer p0);
}
