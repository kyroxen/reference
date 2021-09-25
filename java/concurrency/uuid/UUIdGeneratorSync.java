package corejava.concurrency.uuid;

public class UUIdGeneratorSync {

    private int uuid;

    public UUIdGeneratorSync() {
        this.uuid = 0;
    }

    public synchronized int generateNew() {
        uuid = uuid + 1;
        return uuid;
    }
}
