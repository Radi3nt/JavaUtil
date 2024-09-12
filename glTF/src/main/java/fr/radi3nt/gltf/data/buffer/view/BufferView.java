package fr.radi3nt.gltf.data.buffer.view;

import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.data.buffer.GlTFBuffer;

import java.nio.ByteBuffer;

public class BufferView {

    private final int buffer;
    private final int length;
    private final long offset;

    public BufferView(int buffer, int length, long offset) {
        this.buffer = buffer;
        this.length = length;
        this.offset = offset;
    }

    public ByteBuffer getBuffer(GlTFResult result) {
        GlTFBuffer currentBuffer = result.buffers[buffer];

        return currentBuffer.read(offset, length);
    }

    public void getBuffer(GlTFResult result, ByteBuffer resultBuff) {
        GlTFBuffer currentBuffer = result.buffers[buffer];
        currentBuffer.read(offset, length, resultBuff);
    }
}
