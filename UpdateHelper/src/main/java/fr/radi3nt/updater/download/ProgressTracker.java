package fr.radi3nt.updater.download;

public class ProgressTracker {

    private long byteSize = 0;

    public long getByteSize() {
        return byteSize;
    }

    public void setByteSize(long byteSize) {
        this.byteSize = byteSize;
    }
}
