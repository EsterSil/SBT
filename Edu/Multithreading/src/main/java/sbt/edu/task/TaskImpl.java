package sbt.edu.task;

import sbt.edu.task.Task;

import java.util.concurrent.Callable;

public class TaskImpl<T> implements Task {

    private final Callable<T> callable;
    private volatile T result;
    private  boolean inProgress;
    private TaskPerformingException thrown;

    public TaskImpl(Callable<T> callable) {
        this.callable = callable;
    }

    @Override
    public T get() throws Exception {
        T call = result;
        Exception exception = thrown;
        if (call == null) {
            if (exception == null) {
                await();
                try {
                    call = doCall();
                } catch (Exception e) {
                    thrown = new TaskPerformingException(e);
                    throw thrown;
                }
            } else {
                throw thrown;
            }
        }

        return call;
    }

    private synchronized T doCall() throws Exception {
        T call = null;
        if (result == null) {
            inProgress = true;
            call = result = callable.call();
            inProgress = false;
        }
        //call = result;
        return call;
    }

    private synchronized void await() {
        while (inProgress) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
