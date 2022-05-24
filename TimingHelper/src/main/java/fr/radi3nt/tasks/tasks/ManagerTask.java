package fr.radi3nt.tasks.tasks;

public interface ManagerTask {

    void run();

    boolean hasStarted();
    boolean isFinished();

    long getId();

}
