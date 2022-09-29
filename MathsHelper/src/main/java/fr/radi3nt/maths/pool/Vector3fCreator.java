package fr.radi3nt.maths.pool;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class Vector3fCreator implements ObjectPool<Vector3f> {

    @Override
    public Vector3f borrow() {
        return new SimpleVector3f();
    }

    @Override
    public void free(Vector3f object) {

    }
}
