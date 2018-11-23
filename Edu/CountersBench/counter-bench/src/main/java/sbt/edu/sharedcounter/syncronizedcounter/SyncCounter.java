package sbt.edu.sharedcounter.syncronizedcounter;

import sbt.edu.sharedcounter.SharedCounter;

public class SyncCounter implements SharedCounter {

    private volatile Integer counter;
    //private static final Integer lock;

    public SyncCounter() {
        this.counter = 0;
        //lock = 0;
    }


    public int get() {
        return counter;
    }



    public int getAndIncrement() {
        Integer currentValue = 0;
        Integer newValue = 0;
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

    @Override
    public void reset() {
        synchronized (counter) {
            counter = 0;
        }
    }
}
