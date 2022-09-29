package fr.radi3nt.maths.pool;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class SoloVector3fPool implements ObjectPool<Vector3f> {

    private final Vector3f vector3f = new SimpleVector3f();

    @Override
    public Vector3f borrow() {
        return vector3f;
    }

    @Override
    public void free(Vector3f object) {

    }
}
