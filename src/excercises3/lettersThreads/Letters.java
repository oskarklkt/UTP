package excercises3.lettersThreads;

import java.util.ArrayList;
import java.util.List;

public class Letters {

    private final List<Thread> threads;

    public Letters(String letters) {
        char[] letters1 = letters.toCharArray();
        threads = new ArrayList<>();
        for (char letter : letters1) {
            threads.add(new Thread(
                    () -> {
                        try {
                            while (!Thread.currentThread().isInterrupted()) {
                                Thread.sleep(1000);
                                System.out.print(letter);
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }, "Thread " + letter));
        }
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void stop() {
        for (Thread t : threads) {
            t.interrupt();
        }
    }
}
