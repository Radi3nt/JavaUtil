package fr.radi3nt.updater;

import fr.radi3nt.file.files.WritableFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public abstract class ReadableByteChannelUpdater implements JavaUpdater {

    protected final WritableFile file;

    protected ReadableByteChannelUpdater(WritableFile file) {
        this.file = file;
    }

    /*
    @Override
    public void update() {
        ReadableByteChannel channel = createReadableChannel();

        try {
            if (!file.isCreated()) {
                file.create();
            } else {
                file.delete();
            }

            OutputStream outputSteam = file.getOutputSteam();

            ByteBuffer buf = ByteBuffer.allocate(1024*1024);


            int len = channel.read(buf);
            while (len != -1) {
                buf.flip();
                outputSteam.write(buf.array());

                buf.clear();

                len = channel.read(buf);
            }
            channel.close();
            outputSteam.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

     */

    @Override
    public void update() {
        ReadableByteChannel channel = createReadableChannel();

        try {
            if (!file.isCreated()) {
                file.create();
            } else {
                file.delete();
            }

            OutputStream outputSteam = file.getOutputSteam();

            ByteBuffer buf = ByteBuffer.allocate(1024*1024);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            int len = channel.read(buf);
            while (len != -1) {
                buf.flip();
                writeToBuffer(byteArrayOutputStream, buf);
                buf.clear();

                len = channel.read(buf);
            }
            channel.close();

            System.out.println("Finished download");

            outputSteam.write(byteArrayOutputStream.toByteArray());

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

    protected abstract ReadableByteChannel createReadableChannel();
}
