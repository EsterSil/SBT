package sbt.edu.barrier;

public class BarrierImpl implements Barrier {

    private final int volume;
    private volatile int in;
    private volatile int out;

    public BarrierImpl(int volume) {
        this.volume = volume;
        out = volume;
    }

    public synchronized long await() {
        long time = System.currentTimeMillis();
        in++;
        while (in < volume) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        release();
        return  time;
    }

    private void release() {
        out--;
        if(out == 0) {
            resetBarrier();
            System.out.println( "sbt.edu.barrier.Barrier released");
        }
        notifyAll();
    }

    private void resetBarrier() {
        out = volume;
        in = 0;
    }
}
