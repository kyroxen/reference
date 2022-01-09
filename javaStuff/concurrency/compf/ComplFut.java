package javaStuff.concurrency.compf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ComplFut {
    private static String HELLO = "Hello ";

    // create an instance of the ThreadPoolExecutor
    static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {

        System.out.println("Execution started Thread: " + Thread.currentThread().getName());
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> myRunnable());
        delay(100);
        System.out.println("Calling cancel");
        future.cancel(true);
        delay(10000);
        System.out.printf("%nThreadPoolExecutor statuses:%nshutdown: %s %nterminated: %s",
                threadPoolExecutor.isShutdown(),
                threadPoolExecutor.isTerminated()
        );
    }

    private static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.printf("%n[delay()]Caught interruption in thread %s, statuses: %nshutdown: %s %nterminated: %s",
                    Thread.currentThread().getName(),
                    Thread.currentThread().isInterrupted(),
                    Thread.currentThread().isAlive()
            );
        }
    }

    private static void myRunnable() {
        Map<Integer, CompletableFuture<String>> map = new HashMap<>();
        for (int i = 0; i < 35; i++) {
            int finalI = i;
            CompletableFuture<String> e = CompletableFuture.supplyAsync(() -> getResponse(finalI), threadPoolExecutor);
            map.put(i, e);
        }
        CompletableFuture<Void> all = CompletableFuture.allOf(map.values().toArray(new CompletableFuture[0]));
        try {
            all.get();
        } catch (InterruptedException e) {
            delay(100);
            System.out.printf("%nCaught interruption in thread %s, statuses: %ninterrupt: %s%nalive: %s%n",
                    Thread.currentThread().getName(), Thread.currentThread().isInterrupted(), Thread.currentThread().isAlive());
            delay(100);
            Thread.currentThread().interrupt();
            System.out.printf("%nManually interrupted thread %s, statuses: %ninterrupt: %s%nalive: %s%n",
                    Thread.currentThread().getName(), Thread.currentThread().isInterrupted(), Thread.currentThread().isAlive());
        } catch (Exception e) {
            System.out.println("Caught another exception");
            e.printStackTrace();
        }

        System.out.println("Printing result! " + Thread.currentThread().getName());
        List<String> collect = new ArrayList<>();
        List<Integer> uncompletedStuff = new ArrayList<>(map.size());
        for (Map.Entry<Integer, CompletableFuture<String>> entry : map.entrySet()) {
            CompletableFuture<String> completableFuture = entry.getValue();
            if (completableFuture.isDone() && !completableFuture.isCompletedExceptionally()) {
                String join = completableFuture.join();
                collect.add(join);
            } else {
                uncompletedStuff.add(entry.getKey());
            }
        }

        for (String s : collect) {
            System.out.println(s);
        }

        for (Integer integer : uncompletedStuff) {
            System.out.println("Unable to process task for the input " + integer);
        }
        System.out.printf("threadPoolExecutor statuses before calling shutdownNow() : shutdown: %s terminated: %s",
                threadPoolExecutor.isShutdown(),
                threadPoolExecutor.isTerminated()
        );
        threadPoolExecutor.shutdownNow();
    }

    private static String getResponse(int param) {
        delay(100);
        if (param == 8 || param == 15) {
            delay(10);
            throw new MyException("param " + param + " is unsuccessful, Thread " + Thread.currentThread().getName());
        }
        return HELLO + param + " Completed by " + Thread.currentThread().getName();
    }
}
