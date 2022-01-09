package javaStuff.concurrency.waitnotify1;

import java.util.Deque;
import java.util.LinkedList;

public class Middleware {

    private final Deque<Message> buffer;
    private final int size;

    public Middleware(int size) {
        this.size = size;
        this.buffer = new LinkedList<>();
    }

    public synchronized void publish(Message message) throws InterruptedException {
        if(message == null)
            return;
        String prefix = "Thread " + Thread.currentThread().getName();
        System.out.println(prefix + " is trying to publish the message " + message);
        while(buffer.size() >= size){
            System.out.println(prefix + " calling wait() for message " + message);
            wait();
            System.out.println(prefix + " has woken up and is in state " + Thread.currentThread().getState());
        }
        System.out.println(prefix + " inserting the message " + message + " in buffer, size: " + buffer.size());
        buffer.offerLast(message);
        notifyAll();
    }

    public synchronized Message consume(){
        String prefix = "Thread " + Thread.currentThread().getName();
        System.out.println(prefix + " is trying to consume");
        while(buffer.isEmpty()){
            System.out.println(prefix + " is waiting to because buffer's length is 0");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(prefix + " has woken up and is in state " + Thread.currentThread().getState());
        }
        System.out.println(prefix + " accessing the buffer");
        Message message = buffer.pollFirst();
        System.out.println(prefix + " got the message " + message);
        System.out.println(prefix + " notifying consumers if they are blocked *Sigh* Is this my job though :(");
        notifyAll();
        return message;
    }
}
