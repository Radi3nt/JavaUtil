package fr.radi3nt.tasks.manager;

import fr.radi3nt.tasks.tasks.ManagerTask;
import fr.radi3nt.timing.TimingUtil;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentListSetTaskManager implements TaskManager {

    private final ConcurrentSkipListSet<ManagerTask> queuedTasks = new ConcurrentSkipListSet<>(Comparator.comparingLong(ManagerTask::getId));
    private final ConcurrentSkipListSet<ManagerTask> persistentTasks = new ConcurrentSkipListSet<>(Comparator.comparingLong(ManagerTask::getId));

    public void update() {
        for (ManagerTask gLTask : queuedTasks) {
            try {
                gLTask.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
            queuedTasks.remove(gLTask);
        }

        for (ManagerTask persistentTask : persistentTasks) {
            try {
                persistentTask.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addTask(ManagerTask ManagerTask) {
        queuedTasks.add(ManagerTask);
    }

    @Override
    public void addWaitTask(ManagerTask basicManagerTask) {
        addTask(basicManagerTask);
        TimingUtil.waitUntilTaskFinished(basicManagerTask);
    }

    @Override
    public void addPersistentTask(ManagerTask ManagerTask) {
        persistentTasks.add(ManagerTask);
    }

}
