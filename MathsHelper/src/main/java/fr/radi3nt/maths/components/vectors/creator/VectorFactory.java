package fr.radi3nt.maths.components.vectors.creator;

import fr.radi3nt.maths.components.vectors.*;

public class VectorFactory { //todo use this to create vectors

    private static VectorCreator vectorCreator;

    public static Vector3f createVector3f(float x, float y, float z) {
        return vectorCreator.createVector3f(x, y, z);
    }

    public static Vector3f createVector3f() {
        return vectorCreator.createVector3f();
    }

    public static Vector3i createVector3i(int x, int y, int z) {
        return vectorCreator.createVector3i(x, y, z);
    }

    public static Vector3i createVector3i() {
        return vectorCreator.createVector3i();
    }

    public static Vector3b createVector3b(byte x, byte y, byte z) {
        return vectorCreator.createVector3b(x, y, z);
    }

    public static Vector3b createVector3b() {
        return vectorCreator.createVector3b();
    }

    public static Vector2f createVector2f(float x, float y) {
        return vectorCreator.createVector2f(x, y);
    }

    public static Vector2f createVector2f() {
        return vectorCreator.createVector2f();
    }

    public static Vector2i createVector2i(int x, int y) {
        return vectorCreator.createVector2i(x, y);
    }

    public static Vector2i createVector2i() {
        return vectorCreator.createVector2i();
    }

    public static void setVectorCreator(VectorCreator vectorCreator) {
        VectorFactory.vectorCreator = vectorCreator;
    }
}
