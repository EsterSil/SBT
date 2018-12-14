package sbt.edu.barrier;

import sbt.edu.barrier.Barrier;

public class RunnerMain implements Runnable {
    private int coordinate;
    private final int runningSpeed;
    private final int distance;
    private final Barrier barrier;
    private long startTime;
    private long endTime;
    private long barrierTime;

    public RunnerMain(int runningSpeed, int distance, Barrier barrier) {
        this.runningSpeed = runningSpeed;
        this.distance = distance;
        this.barrier = barrier;
    }

    public void run() {
        doRun();
        barrierTime = barrier.await();
        printRunningTime();
    }

    private void printRunningTime() {
        endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + ": reached barrier at " + (barrierTime - startTime) +
                " Im finished at " + (endTime - startTime));
    }

    private void doRun() {
        startTime = System.currentTimeMillis();
        while (coordinate < distance - runningSpeed) {
            coordinate += runningSpeed;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
