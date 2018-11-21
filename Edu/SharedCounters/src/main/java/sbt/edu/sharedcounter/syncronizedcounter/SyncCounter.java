package sbt.edu.sharedcounter.syncronizedcounter;

import sbt.edu.sharedcounter.SharedCounter;

public class SyncCounter implements SharedCounter {

    private volatile Long counter;
    //private static final Integer lock;

    public SyncCounter() {
        this.counter = 0L;
        //lock = 0;
    }


    public long get() {
        return counter;
    }

    public long getAndIncrement() {
        Long currentValue = 0L;
        Long newValue = 0L;
        while (true) {
            currentValue = counter;
            newValue = currentValue + 1;
            synchronized (counter) {
                if (counter.equals(currentValue)) {
                    counter = newValue;
                    return currentValue;
                }
            }

        }
    }
}
