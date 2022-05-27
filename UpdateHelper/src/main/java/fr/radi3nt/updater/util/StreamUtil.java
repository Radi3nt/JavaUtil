package fr.radi3nt.updater.util;

import fr.radi3nt.updater.download.ProgressTracker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class StreamUtil {

    public static String read(InputStream in) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = in.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }

        return result.toString(StandardCharsets.UTF_8.toString());
    }

    public static void transfer(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        for (int length; (length = in.read(buffer)) != -1; ) {
            out.write(buffer, 0, length);
        }
    }

    public static void transfer(InputStream in, OutputStream out, ProgressTracker progressTracker) throws IOException {
        byte[] buffer = new byte[1024];
        for (int length; (length = in.read(buffer)) != -1; ) {
            out.write(buffer, 0, length);
            progressTracker.setByteSize(progressTracker.getByteSize() + length);
        }
    }

}
