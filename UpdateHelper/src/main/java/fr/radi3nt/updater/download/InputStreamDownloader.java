package fr.radi3nt.updater.download;

import fr.radi3nt.file.files.WritableFile;
import fr.radi3nt.updater.util.StreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public abstract class InputStreamDownloader {

    protected final WritableFile file;

    protected InputStreamDownloader(WritableFile file) {
        this.file = file;
    }

    public void update(ProgressTracker progressTracker) {
        InputStream channel = createInputStream();

        try {
            if (file.isCreated()) {
                file.delete();
            }

            file.create();

            OutputStream outputSteam = file.getOutputSteam();

            StreamUtil.transfer(channel, outputSteam, progressTracker);

            channel.close();
            outputSteam.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToBuffer(OutputStream outputSteam, ByteBuffer buf) throws IOException {
        for (int i = 0; i < buf.limit(); i++) {
            outputSteam.write(buf.get());
        }
    }

    protected abstract InputStream createInputStream();
}
