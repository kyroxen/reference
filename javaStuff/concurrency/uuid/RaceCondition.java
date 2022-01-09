package javaStuff.concurrency.uuid;

import java.util.*;

public class RaceCondition {
    static final int DISTINCT_THREADS = 6;

    public static void main(String[] args) {
        System.out.println("Main thread with name: " + Thread.currentThread().getName() + " has started");

        UUIdGenerator uuidGenerator = new UUIdGenerator();
        List<CustomThread> threadList = new ArrayList<>();

        createThreads(uuidGenerator, threadList);
        startThreads(threadList);
        joinThreads(threadList);
        printResult(threadList);

        System.out.println("Main thread with name: " + Thread.currentThread().getName() + " has ended");
    }

    private static void printResult(List<CustomThread> threadList) {
        Map<Integer, Integer> freqMap = getIdsToFrequencyMap(threadList);
        Map<Integer, List<Integer>> freqToListUuidMap = new HashMap<>();
        freqMap.forEach((k, v) -> freqToListUuidMap.computeIfAbsent(v, val -> new ArrayList<>()).add(k));
        freqToListUuidMap.forEach((k, v) -> System.out.println("Ids generated " + k + " no.of times were: " + v.size()));
    }

    private static Map<Integer, Integer> getIdsToFrequencyMap(List<CustomThread> threadList) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (CustomThread customThread : threadList) {
            customThread.getUuidList().forEach(i -> freqMap.put(i, freqMap.getOrDefault(i, 0) + 1));
        }
        return freqMap;
    }

    private static void createThreads(UUIdGenerator uuidGenerator, List<CustomThread> threadList) {
        for (int i = 0; i < DISTINCT_THREADS; i++) {
            CustomThread customThread = new CustomThread(i, uuidGenerator);
            threadList.add(customThread);
        }
    }

    private static void startThreads(List<CustomThread> threadList) {
        for (CustomThread customThread : threadList) {
            customThread.start();
        }
    }

    private static void joinThreads(List<CustomThread> threadList) {
        int joinedSize = 0;
        int failedSize = 0;
        Set<Long> completedThreadSet = new HashSet<>();
        while (completedThreadSet.size() < threadList.size()) {
            for (CustomThread customThread : threadList) {
                if (!customThread.isAlive() && !completedThreadSet.contains(customThread.getId())) {
                    System.out.println("Thread " + customThread.getName() + " is in state " + customThread.getState()
                            + " trying to join now");
                    try {
                        customThread.join();
                        System.out.println("Thread " + customThread.getName() + " joined! State= " + customThread.getState());
                    } catch (InterruptedException e) {
                        System.out.println("Caught InterruptedException in main thread! Couldn't join " + customThread.getName());
                        failedSize++;
                    }
                    joinedSize++;
                    completedThreadSet.add(customThread.getId());
                }
            }
        }
        System.out.println("*****************");
        System.out.println("Done! Total Joined size: " + joinedSize + " failedSize: " + failedSize);
        System.out.println("*****************");
    }
}
