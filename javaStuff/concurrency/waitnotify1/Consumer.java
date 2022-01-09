package javaStuff.concurrency.waitnotify1;

public class Consumer extends Thread {

    private final Middleware middleware;

    public Consumer(int id, Middleware middleware) {
        super("custom-thread-consumer-" + id);
        this.middleware = middleware;
    }

    @Override
    public void run() {
        while(true){
            Message message = middleware.consume();
            // do something with the message
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
