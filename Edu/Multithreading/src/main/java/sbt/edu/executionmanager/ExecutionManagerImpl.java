package sbt.edu.executionmanager;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutionManagerImpl implements ExecutionManager {


    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ContextImpl context = new ContextImpl();
        ExecutorService service = Executors.newSingleThreadExecutor();
        CountDownLatch counter = new CountDownLatch(tasks.length);
        service.execute(() -> {
            ExecutorService innerService = Executors.newCachedThreadPool();
            for (int i = 0; i < tasks.length; i++) {
                if (!context.isInterrupted()) {
                    final int currentNum = i;
                    innerService.execute(() -> {
                        try {
                            tasks[currentNum].run();
                            context.incrementCompleteCounter();
                        } catch (RuntimeException e) {
                            context.incrementFailedCounter();
                        } finally {
                            counter.countDown();
                        }
                    });
                } else {
                    innerService.shutdown();
                    try {
                        if (!innerService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                            innerService.shutdownNow();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    context.setInterruptedCounter(tasks.length - i);
                    context.setUpFinished();
                    for (int j = 0; j < tasks.length - i + 1; j++) {
                        counter.countDown();
                    }
                    break;
                }
            }
            innerService.shutdown();
            try {
                counter.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!context.isInterrupted()) {
                callback.run();
            }
            context.setUpFinished();
        });
        service.shutdown();
        return context;
    }
}
