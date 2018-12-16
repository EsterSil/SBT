package sbt.edu.executionmanager;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ContextImpl implements Context {

    private AtomicInteger completedTaskCount = new AtomicInteger();
    private AtomicInteger failedTaskCount = new AtomicInteger();
    private AtomicInteger interruptedTaskCount = new AtomicInteger();
    private AtomicBoolean finished = new AtomicBoolean();
    private AtomicBoolean interrupted = new AtomicBoolean();

    @Override
    public int getCompletedTaskCount() {
        return completedTaskCount.get();
    }

    @Override
    public int getFailedTaskCount() {
        return failedTaskCount.get();
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedTaskCount.get();
    }

    @Override
    public void interrupt() {
        interrupted.getAndSet(true);
    }

    @Override
    public boolean isFinished() {
        return finished.get();
    }


    void incrementCompleteCounter() {
        completedTaskCount.getAndIncrement();
    }
    void incrementFailedCounter() {
        failedTaskCount.getAndIncrement();
    }
    void setInterruptedCounter(int value) {
        interruptedTaskCount.compareAndSet(0,value);
    }

    boolean isInterrupted() {
        return interrupted.get();
    }

    void setUpFinished(){
        finished.set(true);
    }
}
