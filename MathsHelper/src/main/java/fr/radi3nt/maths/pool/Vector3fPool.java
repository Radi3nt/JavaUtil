package fr.radi3nt.maths.pool;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class Vector3fPool extends QueuedPool<Vector3f> {

    public Vector3fPool() {
    }

    public Vector3fPool(int baseObjectAmount) {
        for (int i = 0; i < baseObjectAmount; i++) {
            queue.add(create());
        }
    }

    @Override
    protected Vector3f create() {
        return new SimpleVector3f();
    }

}
