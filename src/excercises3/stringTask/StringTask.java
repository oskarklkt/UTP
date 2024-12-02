package excercises3.stringTask;

public class StringTask implements Runnable {
    private final String word;
    private String result = "";
    private final int repetitions;
    private TaskState state;
    private boolean done;
    private Thread thread;

    public StringTask(String word, int repetitions) {
        this.word = word;
        this.repetitions = repetitions;
        this.state = TaskState.CREATED;
        done = false;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < repetitions; i++) {
                if (done) {
                    state = TaskState.ABORTED;
                    return;
                }
                result += word;
            }
            done = true;
            state = TaskState.READY;
        } catch (Exception e) {
            state = TaskState.ABORTED;
        }
    }

    public void start() {
        if (state == TaskState.CREATED) {
            thread = new Thread(this);
            state = TaskState.RUNNING;
            thread.start();
        }
    }

    public void abort() {
        done = true;
        if (thread != null) {
            state = TaskState.ABORTED;
            thread.interrupt();
        }
    }

    public TaskState getState() {
        return state;
    }

    public String getResult() {
        return result;
    }

    public boolean isDone() {
        return done;
    }
}
