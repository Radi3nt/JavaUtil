package fr.radi3nt.maths.pool;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public abstract class ConcurrentQueuedPool<T> implements ObjectPool<T> {

    protected final Deque<T> queue = new ConcurrentLinkedDeque<>();

    @Override
    public T borrow() {
        T object = queue.pollFirst();
        if (object == null)
            return create();

        return object;
    }

    protected abstract T create();

    @Override
    public void free(T object) {
        queue.addFirst(object);
    }
}
