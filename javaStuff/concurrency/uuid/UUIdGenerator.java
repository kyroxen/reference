package javaStuff.concurrency.uuid;

public class UUIdGenerator {

    private int uuid;

    public UUIdGenerator() {
        this.uuid = 0;
    }

    public int generateNew() {
        uuid = uuid + 1;
        return uuid;
    }
}
