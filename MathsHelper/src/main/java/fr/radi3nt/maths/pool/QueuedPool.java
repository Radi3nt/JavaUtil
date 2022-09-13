package fr.radi3nt.maths.pool;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class QueuedPool<T> implements ObjectPool<T> {

    protected final ConcurrentLinkedQueue<T> queue = new ConcurrentLinkedQueue<>();

    @Override
    public T borrow() {
        T object = queue.poll();
        if (object == null)
            return create();

        return object;
    }

    protected abstract T create();

    @Override
    public void free(T object) {
        queue.add(object);
    }
}
