package fr.radi3nt.gltf.data.buffer.accessor;

import fr.radi3nt.gltf.data.GlTFResult;

import java.nio.ByteBuffer;

public class BufferAccessor {

    private final int bufferView;
    private final GlTFComponentType componentType;
    private final GlTFAccessorType type;
    private final int count;

    private final float[] min;
    private final float[] max;

    public BufferAccessor(int bufferView, GlTFComponentType componentType, GlTFAccessorType type, int count, float[] min, float[] max) {
        this.bufferView = bufferView;
        this.componentType = componentType;
        this.type = type;
        this.count = count;
        this.min = min;
        this.max = max;
    }

    public ByteBuffer getBuffer(GlTFResult result) {
        ByteBuffer buffer = result.bufferViews[bufferView].getBuffer(result);
        buffer.limit(getByteSize());
        return buffer;
    }

    public void getBuffer(GlTFResult result, ByteBuffer buffer) {
        result.bufferViews[bufferView].getBuffer(result, buffer);
    }

    public GlTFAccessorType getType() {
        return type;
    }

    public int getByteSize() {
        return count*type.amount()*componentType.bytes();
    }

    public int getCount() {
        return count;
    }
}
