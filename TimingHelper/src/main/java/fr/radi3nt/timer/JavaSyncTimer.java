package fr.radi3nt.timer;

public class JavaSyncTimer implements SyncTimer {

    private long lastTime = System.nanoTime();

    private double fps;
    private double delta;

    public JavaSyncTimer(double delta) {
        this.delta = delta;
        this.fps = 1d / delta;
    }

    public JavaSyncTimer() {
    }

    @Override
    public void start() {
        lastTime = System.nanoTime();
    }

    @Override
    public void end() {
        long timePassed = System.nanoTime() - lastTime;
        double timePassedInSecond = timePassed / 1_000_000_000d;

        fps = 1d / timePassedInSecond;
        delta = timePassedInSecond;
    }

    @Override
    public double getFps() {
        return fps;
    }

    @Override
    public double getDelta() {
        return delta;
    }
}
