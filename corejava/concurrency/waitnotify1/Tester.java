package corejava.concurrency.waitnotify1;

import java.util.ArrayList;
import java.util.List;

public class Tester {
    private static int MESSAGE_COUNT = 10;
    public static void main(String[] args) {
        Middleware middleware = new Middleware(3);
        int publisherThreadCount = 3;
        int consumerThreadCount = 2;
        List<Publisher> publishers = createPublishers(publisherThreadCount, middleware);
        List<Consumer> consumers = createConsumers(consumerThreadCount, middleware);
        start(publishers);
        start(consumers);
    }

    private static <T extends Thread> void start(List<T> publishers) {
        for (T thread : publishers) {
            System.out.println("Starting thread " + thread.getName());
            thread.start();
        }
    }

    private static List<Consumer> createConsumers(int consumerThreadCount, Middleware middleware) {
        List<Consumer> consumers = new ArrayList<>();
        for(int i = 1 ; i <= consumerThreadCount; i++)
            consumers.add(new Consumer(i, middleware));
        return consumers;
    }

    private static List<Publisher> createPublishers(int publisherThreadCount, Middleware middleware) {
        List<Publisher> publishers = new ArrayList<>();
        for(int i = 1 ; i <= publisherThreadCount; i++)
            publishers.add(new Publisher(MESSAGE_COUNT, i, middleware));
        return publishers;
    }
}
