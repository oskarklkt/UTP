package excercises3.taskList;

import java.util.Random;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.Future;

public class Task {
    ExecutorService executor;
    private Future<Integer> futureTask;

    public Task(ExecutorService executor){
        this.executor = executor;
    }

    public void runTask() {
        futureTask = executor.submit(()-> {

            Random random = new Random();
            int result = random.nextInt(100) + 1;
            int randomSleep = (random.nextInt(10) + 1) * 10;

            try{
                Thread.sleep(randomSleep * 1000);
            } catch (Exception e){
                e.getMessage();
            }
            return result;
        });
    }
    public Future<Integer> getFutureTask(){
        return futureTask;
    }
}


