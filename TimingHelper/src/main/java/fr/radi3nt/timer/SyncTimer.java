package fr.radi3nt.timer;

public interface SyncTimer {

    default void update() {
        end();
        start();
    }

    void start();

    void end();

    double getFps();

    double getDelta();

}