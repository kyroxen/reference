package javaStuff.concurrency.uuid;

import java.util.ArrayList;
import java.util.List;

public class CustomThread extends Thread {

    private final List<Integer> uuidList;
    private final UUIdGenerator uuIdGenerator;

    CustomThread(int numberName, UUIdGenerator uuIdGenerator) {
        super();
        this.setName("custom-thread-" + numberName);
        this.uuIdGenerator = uuIdGenerator;
        this.uuidList = new ArrayList<>();

    }

    @Override
    public void run() {
        System.out.println("Thread: " + this.getName() + " is running");
        int i = 10000;
        while (i > 0) {
            uuidList.add(uuIdGenerator.generateNew());
            i--;
        }
        System.out.println("Thread: " + this.getName() + " has finished");
    }

    public List<Integer> getUuidList() {
        return uuidList;
    }
}