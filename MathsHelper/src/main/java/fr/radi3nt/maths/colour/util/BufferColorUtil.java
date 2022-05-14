package fr.radi3nt.maths.colour.util;

import java.nio.ByteBuffer;

public class BufferColorUtil {

    public static void encode(ByteBuffer buffer, int byteOffset, float red, float green, float blue, float alpha) {
        byteOffset = storeRGB(buffer, byteOffset, red, green, blue);
        storeColor(buffer, byteOffset, alpha);
    }

    public static int storeRGB(ByteBuffer buffer, int byteOffset, float red, float green, float blue) {
        storeColor(buffer, byteOffset, red);
        byteOffset += Byte.BYTES;

        storeColor(buffer, byteOffset, green);
        byteOffset += Byte.BYTES;

        storeColor(buffer, byteOffset, blue);
        byteOffset += Byte.BYTES;

        return byteOffset;
    }

    public static void storeColor(ByteBuffer buffer, int byteOffset, float color) {
        buffer.put(byteOffset, (byte) (color * Byte.MAX_VALUE * 2 - Byte.MAX_VALUE));
    }

}
