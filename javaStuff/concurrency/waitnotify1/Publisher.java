package javaStuff.concurrency.waitnotify1;


public class Publisher extends Thread {

    private final int messagePublishCount;
    private final Middleware middleware;

    public Publisher(int messagePublishCount, int id, Middleware middleware) {
        super("custom-thread-publisher-" + id);
        this.messagePublishCount = messagePublishCount;
        this.middleware = middleware;
    }

    @Override
    public void run() {
        int i = messagePublishCount;
        while (i > 0) {
            Message message = new Message(i, Thread.currentThread().getName(), "message:" + Math.random());
            try {
                middleware.publish(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }
    }
}
