package fr.radi3nt.maths.pool;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class QueuedPool<T> implements ObjectPool<T> {

    protected final Deque<T> queue = new ArrayDeque<>();

    @Override
    public T borrow() {
        T object = queue.pollLast();
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
