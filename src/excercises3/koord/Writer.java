package excercises3.koord;

import java.util.concurrent.BlockingQueue;

public class Writer implements Runnable {
    private final BlockingQueue<String> blockingQueue;

    public Writer(Author author) {
        this.blockingQueue = author.getBlockingQueue();
    }

    @Override
    public void run() {
        try {
            String text = blockingQueue.take();
            while (!"null".equals(text)) {
                System.out.println(text);
                text = blockingQueue.take();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
