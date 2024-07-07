package fr.radi3nt.maths.colour.util;

import fr.radi3nt.maths.colour.colors.BasicColour;
import fr.radi3nt.maths.colour.colors.Colour;

import java.nio.ByteBuffer;

public final class ColorUtil {

    public static Colour hex2Rgb(String colorStr) {
        return new BasicColour(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }

    public static void encode(ByteBuffer buffer, float red, float green, float blue, float alpha) {
        storeRGB(buffer, red, green, blue);
        storeQuantizedFloat(buffer, alpha);
    }

    public static void encode(ByteBuffer buffer, int byteOffset, float red, float green, float blue, float alpha) {
        byteOffset = storeRGB(buffer, byteOffset, red, green, blue);
        storeQuantizedFloat(buffer, byteOffset, alpha);
    }

    public static int storeRGB(ByteBuffer buffer, int byteOffset, float red, float green, float blue) {
        storeQuantizedFloat(buffer, byteOffset, red);
        byteOffset += Byte.BYTES;

        storeQuantizedFloat(buffer, byteOffset, green);
        byteOffset += Byte.BYTES;

        storeQuantizedFloat(buffer, byteOffset, blue);
        byteOffset += Byte.BYTES;

        return byteOffset;
    }

    public static void storeRGB(ByteBuffer buffer, float red, float green, float blue) {
        storeQuantizedFloat(buffer, red);
        storeQuantizedFloat(buffer, green);
        storeQuantizedFloat(buffer, blue);
    }

    public static void storeQuantizedFloat(ByteBuffer buffer, float color) {
        buffer.put((byte) (color * Byte.MAX_VALUE * 2 - Byte.MAX_VALUE));
    }

    public static void storeQuantizedFloat(ByteBuffer buffer, int byteOffset, float color) {
        buffer.put(byteOffset, (byte) (color * Byte.MAX_VALUE * 2 - Byte.MAX_VALUE));
    }

}
