package fr.radi3nt.maths.pool;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class WaitingVector3fPool extends QueuedPool<Vector3f> {

    public WaitingVector3fPool(int baseObjectAmount) {
        for (int i = 0; i < baseObjectAmount; i++) {
            queue.add(create());
        }
        queue.notifyAll();
    }

    public Vector3f borrow() {
        Vector3f object = queue.poll();
        if (object == null)
            while ((object = queue.poll()) == null) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        return object;
    }

    @Override
    protected Vector3f create() {
        return new SimpleVector3f();
    }

}
