import sbt.edu.sharedcounter.SharedCounter;


/**
 * test task for shared counter
 * involved thread calls <code> SharedCounter's </code> method  <code> getAndIncrement()</code>
 * exactly <code> bound </code> times
 */
public class CounterUserTask implements Runnable{
    private int bound;
    private SharedCounter counter;
    public CounterUserTask(SharedCounter counter, int bound) {
        this.counter = counter;
        this.bound = bound;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " started");
        for (int i = 0; i< bound; i++) {
            try {
                counter.getAndIncrement();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
