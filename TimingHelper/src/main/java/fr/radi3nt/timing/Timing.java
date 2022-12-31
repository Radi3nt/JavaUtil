package fr.radi3nt.timing;

public class Timing {

    private final long startedTime;
    private final long expireTime;

    public Timing(long startedTime, long expireTime) {
        this.startedTime = startedTime;
        this.expireTime = expireTime;
    }

    public static Timing from(TimeConvention time) {
        return new Timing(System.currentTimeMillis(), time.getMillis());
    }

    public static Timing from(TimeConvention... time) {
        long current = 0;
        for (TimeConvention enumTimeConventions : time) {
            current+=enumTimeConventions.getMillis();
        }

        return new Timing(System.currentTimeMillis(), current);
    }

    public static Timing from(long time) {
        return new Timing(System.currentTimeMillis(), time);
    }

    public static Timing from(int amount, TimeConvention... time) {
        long current = 0;
        for (TimeConvention enumTimeConventions : time) {
            current+=enumTimeConventions.getMillis()*amount;
        }

        return new Timing(System.currentTimeMillis(), current);
    }

    public void waitUtilExpired() {
        synchronized (this) {
            try {
                this.wait(remainingTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isExpired() {
        return System.currentTimeMillis()-startedTime>=expireTime;
    }

    public long remainingTime() {
        return expireTime - (System.currentTimeMillis()-startedTime);
    }
}
