package javaStuff.concurrency.uuid;

import java.util.concurrent.atomic.AtomicInteger;

public class UUIdGeneratorAtomic {

    private AtomicInteger uuid;

    public UUIdGeneratorAtomic() {
        this.uuid = new AtomicInteger(0);
    }

    public int generateNew() {
        return uuid.getAndAdd(1);
    }
}
