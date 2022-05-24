package fr.radi3nt.tasks.tasks;

import fr.radi3nt.tasks.manager.TaskManager;

public class BasicManagerTask extends TemplateManagerTask {

    private final Runnable runnable;

    public BasicManagerTask(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    protected void run_() {
        runnable.run();
    }

    public BasicManagerTask add(TaskManager GLTaskManager) {
        GLTaskManager.addTask(this);
        return this;
    }
}
