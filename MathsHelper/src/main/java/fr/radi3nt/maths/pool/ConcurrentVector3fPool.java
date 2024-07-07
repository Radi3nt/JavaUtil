package fr.radi3nt.maths.pool;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class ConcurrentVector3fPool extends ConcurrentQueuedPool<Vector3f> {

    public ConcurrentVector3fPool() {
    }

    public ConcurrentVector3fPool(int baseObjectAmount) {
        for (int i = 0; i < baseObjectAmount; i++) {
            queue.add(create());
        }
    }

    @Override
    protected Vector3f create() {
        return new SimpleVector3f();
    }

}
