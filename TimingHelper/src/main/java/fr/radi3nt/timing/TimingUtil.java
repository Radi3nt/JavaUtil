package fr.radi3nt.timing;

import fr.radi3nt.tasks.tasks.ManagerTask;

public class TimingUtil {

    public static void waitUntilTaskFinished(ManagerTask gLTask) {
        try {
            gLTask.waitTillFinished();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitMillis(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitNanos(int i) {
        try {
            Thread.sleep(0, i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void wait(int millis, int nano) {
        try {
            Thread.sleep(millis, nano);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
