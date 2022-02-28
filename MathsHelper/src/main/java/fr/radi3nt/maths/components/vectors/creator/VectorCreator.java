package fr.radi3nt.maths.components.vectors.creator;

import fr.radi3nt.maths.components.vectors.*;

public interface VectorCreator {

    Vector3f createVector3f(float x, float y, float z);
    Vector3f createVector3f();

    Vector3i createVector3i(int x, int y, int z);
    Vector3i createVector3i();

    Vector3b createVector3b(byte x, byte y, byte z);
    Vector3b createVector3b();

    Vector2f createVector2f(float x, float y);
    Vector2f createVector2f();

    Vector2i createVector2i(int x, int y);
    Vector2i createVector2i();
}
