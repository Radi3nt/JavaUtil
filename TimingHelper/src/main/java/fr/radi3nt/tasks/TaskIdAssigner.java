package fr.radi3nt.tasks;

import java.util.concurrent.atomic.AtomicLong;

public final class TaskIdAssigner {

    private static final AtomicLong atomicLong = new AtomicLong(0);

    public static long getNewId() {
        return atomicLong.getAndIncrement();
    }

}
