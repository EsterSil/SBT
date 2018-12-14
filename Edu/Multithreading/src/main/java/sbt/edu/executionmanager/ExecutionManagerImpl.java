package sbt.edu.executionmanager;

import java.util.concurrent.*;

public class ExecutionManagerImpl implements ExecutionManager {


    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ContextImpl context = new ContextImpl();
        Thread thread = new Thread(() -> {
            ExecutorService executor = Executors.newFixedThreadPool(10);
            ArrayBlockingQueue<Future> futures = new ArrayBlockingQueue<>(10);
            Thread watcher = new Thread(() -> {
                OUTER: while (!context.isInterrupted() && !context.isFinished()) {
                    for (Future f : futures) {
                        if (f.isDone()) {
                            try {
                                f.get();
                                context.incrementCompleteCounter();
                            } catch (InterruptedException e) {
                                break OUTER;
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                                context.incrementFailedCounter();
                            } finally {
                                futures.remove(f);
                            }
                        }
                    }
                }
                if (context.isInterrupted()){
                    for (Future f : futures) {
                        f.cancel(true);
                        context.incrementInterruptedCounter();
                    }
                }
            });
            watcher.start();
            for (Runnable r : tasks) {
                futures.add(executor.submit(r));

            }
            executor.shutdown();
            try {
                if (!executor.awaitTermination(8000, TimeUnit.MILLISECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
            callback.run();
            context.setUpFinished();
        });
        thread.start();

        return context;
    }
}
