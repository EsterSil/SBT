package sbt.edu.sharedcounter.concurrentcounter;

import sbt.edu.sharedcounter.SharedCounter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ConCounter implements SharedCounter {

    private AtomicLong counter;

    public ConCounter() {
        counter = new AtomicLong(0);
    }

    public long get() {
        return counter.get();
    }

    public long getAndIncrement() {
        return counter.getAndIncrement();
    }
}
