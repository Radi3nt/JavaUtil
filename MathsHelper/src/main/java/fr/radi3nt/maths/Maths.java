package fr.radi3nt.maths;

import fr.radi3nt.maths.components.advanced.matrix.ArrayMatrix3x3;
import fr.radi3nt.maths.components.advanced.matrix.Matrix3x3;
import fr.radi3nt.maths.components.vectors.Vector3f;

import java.util.Arrays;
import java.util.Collection;

public final class Maths {

    public static final double EPSILON = 1e-8;

    public static float clamp(float value, float min, float max) {
        return Math.max(Math.min(value, max), min);
    }

    public static long clamp(long value, long min, long max) {
        return Math.max(Math.min(value, max), min);
    }

    public static int clamp(int value, int min, int max) {
        return Math.max(Math.min(value, max), min);
    }

    public static double clamp(double value, double min, double max) {
        return Math.max(Math.min(value, max), min);
    }

    public static double exactDistance(Vector3f vec1, Vector3f vec2) {
        return Math.abs(Math.sqrt(Math.pow(vec1.getX()-vec2.getX(), 2) + Math.pow(vec1.getY() - vec2.getY(), 2) + Math.pow(vec1.getZ() - vec2.getZ(), 2)));
    }

    public static byte[] floatToByteArray(float[] value) {
        byte[] bytes = new byte[value.length*Float.BYTES];
        for (int i = 0; i < value.length; i++) {
            int intBits =  Float.floatToRawIntBits(value[i]);
            for (int a = 0; a < Float.BYTES; a++) {
                bytes[i * 4 + a] = new Integer(intBits).byteValue();
                intBits = intBits >> 8;
            }
        }
        return bytes;
    }

    public static int floatToByteArray(float[] value, byte[] bytes, int offset) {
        int size = 0;

        for (int i = 0; i < value.length; i++) {
            int intBits =  Float.floatToRawIntBits(value[i]);
            for (int a = 0; a < Float.BYTES; a++) {
                bytes[i * 4 + a + offset] = new Integer(intBits).byteValue();
                intBits = intBits >> 8;
                size++;
            }
        }
        return size;
    }

    public static byte[] intToByteArray(int[] value) {
        byte[] bytes = new byte[value.length*Integer.BYTES];
        for (int i = 0; i < value.length; i++) {
            int intBits = value[i];
            for (int a = 0; a < Integer.BYTES; a++) {
                bytes[i * Integer.BYTES + a] = new Integer(intBits).byteValue();
                intBits = intBits >> 8;
            }
        }
        return bytes;
    }

    public static int intToByteArray(int[] value, byte[] bytes, int offset) {
        int size = 0;

        for (int i = 0; i < value.length; i++) {
            int intBits = value[i];
            for (int a = 0; a < Integer.BYTES; a++) {
                bytes[i * Integer.BYTES + a + offset] = new Integer(intBits).byteValue();
                intBits = intBits >> 8;
                size++;
            }
        }
        return size;
    }

    public static byte[] intToByteArray(Collection<Integer> value) {
        byte[] bytes = new byte[value.size()*Integer.BYTES];
        int i = 0;
        for (Integer intBits : value) {
            int actualByte = intBits;
            for (int a = 0; a < Integer.BYTES; a++) {
                bytes[i * Integer.BYTES + a] = new Integer(actualByte).byteValue();
                actualByte = actualByte >> 8;
            }
            i++;
        }
        return bytes;
    }

    public static float lerp(float a, float b, float f) {
        return a + f * (b - a);
    }

    public static float fma(float a, float b, float c) {
        return a * b + c;
    }

    public static Matrix3x3 skew(Vector3f vec) {
        Matrix3x3 matrix3x3 = new ArrayMatrix3x3();
        matrix3x3.set(0, 0, 0);
        matrix3x3.set(1, 0, -vec.getZ());
        matrix3x3.set(2, 0, vec.getY());

        matrix3x3.set(0, 1, vec.getZ());
        matrix3x3.set(1, 1, 0);
        matrix3x3.set(2, 1, -vec.getX());

        matrix3x3.set(0, 2, -vec.getY());
        matrix3x3.set(1, 2, vec.getX());
        matrix3x3.set(2, 2, 0);
        return matrix3x3;
    }

    public static double[] nCopies(int amount, double value) {
        double[] array = new double[amount];
        Arrays.fill(array, value);
        return array;
    }
}
