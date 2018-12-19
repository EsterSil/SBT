package sbt.edu.task;


import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskImpl<T> implements Task {

    private final Callable<T> callable;
    private final Semaphore semaphore;
    private T executionResult;
    private TaskPerformingException executionException;
    private volatile AtomicBoolean executed;

    public TaskImpl(Callable<T> callable) {
        this.callable = callable;
        this.semaphore = new Semaphore(1);
        this.executed = new AtomicBoolean();
    }

    @Override
    public Object get() throws Exception {
        if (executed.get()) {
            return getExecutionResult();
        }

        semaphore.acquire();

        if (executed.get()) {
            semaphore.release();
            return getExecutionResult();
        }

        try {
            executionResult = callable.call();
            return executionResult;
        } catch (RuntimeException e) {
            executionException = new TaskPerformingException(e);
            throw executionException;
        } finally {
            executed.getAndSet(true);
            semaphore.release();
        }
    }

    private T getExecutionResult() {
        if (executionException != null) {
            throw executionException;
        }
        return executionResult;
    }

}
