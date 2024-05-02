package fr.radi3nt.maths.pool;

import java.util.ArrayList;
import java.util.List;

public abstract class ListPool<T> implements ObjectPool<T> {

    protected final List<T> queue = new ArrayList<>();

    @Override
    public T borrow() {
        if (queue.isEmpty())
            return create();

        T object = queue.remove(queue.size()-1);
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
