package sbt.edu.semaphore;

import sbt.edu.semaphore.Semaphore;

public class SemaphoreImpl implements Semaphore {

    private final int volume;

    private int current;

    public SemaphoreImpl(int volume) {
        this.volume = volume;
    }


    public synchronized void acquire() {
        while (current >= volume) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        current++;

    }


    public synchronized void release() {
        --current;
        synchronized (this) {
            notify();
        }
    }
}
