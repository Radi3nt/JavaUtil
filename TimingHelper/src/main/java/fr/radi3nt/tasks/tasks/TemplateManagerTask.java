package fr.radi3nt.tasks.tasks;

import fr.radi3nt.tasks.TaskIdAssigner;

public abstract class TemplateManagerTask implements ManagerTask {

    private final long id;
    private boolean started = false;
    private boolean finished = false;

    public TemplateManagerTask(long id) {
        this.id = id;
    }

    public TemplateManagerTask() {
        this.id = TaskIdAssigner.getNewId();
    }

    @Override
    public void run() {
        started = true;
        run_();
        finished = true;
    }

    protected abstract void run_();

    @Override
    public boolean hasStarted() {
        return started;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {" +
                "started=" + started +
                ", finished=" + finished +
                ", id=" + id +
                '}';
    }
}
