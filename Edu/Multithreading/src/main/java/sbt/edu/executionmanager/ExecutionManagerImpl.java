package sbt.edu.executionmanager;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutionManagerImpl implements ExecutionManager {
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        Thread thread = new Thread(() -> {
            ExecutorService executor = Executors.newFixedThreadPool(10);
            for (Runnable r: tasks) {
                executor.execute(r);
            }
            executor.shutdown();
        }) ;


        return null;
    }
}
