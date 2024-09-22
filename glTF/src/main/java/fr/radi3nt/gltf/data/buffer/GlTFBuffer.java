package fr.radi3nt.gltf.data.buffer;

import fr.radi3nt.file.files.ReadableFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class GlTFBuffer {

    private final ReadableFile readableFile;
    private final long byteLength;

    public GlTFBuffer(ReadableFile readableFile, long byteLength) {
        this.readableFile = readableFile;
        this.byteLength = byteLength;
    }

    public ByteBuffer read(long offset, int length) {
        try {
            return tryReading(offset, length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ByteBuffer tryReading(long offset, int length) throws IOException {
        InputStream inputStream = readableFile.getInputStream();
        long totalSkipped = 0;
        while (totalSkipped < offset) {
            totalSkipped+=inputStream.skip(offset -totalSkipped);
        }
        byte[] bytes = readNBytes(inputStream, length);
        inputStream.close();

        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        return wrap;
    }

    public void read(long offset, int length, ByteBuffer resultBuff) {
        try {
            tryReading(offset, length, resultBuff);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void tryReading(long offset, int length, ByteBuffer result) throws IOException {
        InputStream inputStream = readableFile.getInputStream();
        long totalSkipped = 0;
        while (totalSkipped < offset) {
            totalSkipped+=inputStream.skip(offset -totalSkipped);
        }
        byte[] bytes = readNBytes(inputStream, length);
        inputStream.close();

        result.put(bytes);
    }


    private byte[] readNBytes(InputStream stream, int length) {
        byte[] bytes = new byte[length];

        int readBytes = 0;
        while (readBytes < length) {
            try {
                readBytes+=stream.read(bytes, readBytes, length-readBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return bytes;
    }
}
