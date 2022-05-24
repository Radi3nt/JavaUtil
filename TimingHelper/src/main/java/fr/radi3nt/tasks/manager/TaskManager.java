package fr.radi3nt.tasks.manager;

import fr.radi3nt.tasks.tasks.ManagerTask;

public interface TaskManager {

    void addTask(ManagerTask ManagerTask);
    void addWaitTask(ManagerTask basicManagerTask);
    void addPersistentTask(ManagerTask ManagerTask);
}
