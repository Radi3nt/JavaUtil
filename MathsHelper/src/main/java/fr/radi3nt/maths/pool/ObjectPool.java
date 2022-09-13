package fr.radi3nt.maths.pool;

public interface ObjectPool<T> {

    T borrow();

    void free(T object);

}
