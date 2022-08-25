package fr.radi3nt.timing;

public class TimePerformanceTest {

    private long started;
    private long ended;

    public void start() {
        started = System.nanoTime();
    }

    public void end() {
        ended = System.nanoTime();
    }

    public long getResult() {
        return ended - started;
    }


}
