package excercises3.koord;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Author implements Runnable {
    private final String[] texts;
    private final BlockingQueue<String> blockingQueue;

    public Author(String[] texts) {
        this.texts = texts;
        this.blockingQueue = new ArrayBlockingQueue<>(5);
    }

    @Override
    public void run() {
        int i = 0;
        try {
            while (i < texts.length) {
                blockingQueue.put(texts[i++]);
                Thread.sleep(1000);
            }
            blockingQueue.put("null");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public BlockingQueue<String> getBlockingQueue() {
        return blockingQueue;
    }
}
