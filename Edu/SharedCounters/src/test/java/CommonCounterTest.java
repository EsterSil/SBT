
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sbt.edu.sharedcounter.SharedCounter;
import sbt.edu.sharedcounter.syncronizedcounter.SyncCounter;

import java.util.concurrent.*;

/**
 * these tests are intended to confirm correctness of counters designed
 *
 * every thread runs simple task {@link CounterUserTask}
 */
class CommonCounterTest {

    private int bound;
    public CommonCounterTest(int bound) {
        this.bound = bound;
    }

    /**
     * single thread consistency
     * @throws InterruptedException
     */

    void consistencyTest(SharedCounter counter) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(new CounterUserTask(counter, bound));
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        Assertions.assertEquals(bound, counter.get());
    }


    /**
     * two thread consistency test
     * @throws InterruptedException
     * @throws ExecutionException
     */

    void lowContentionConsistencyTest(SharedCounter counter) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for(int i = 0; i<2; i++) {
            executor.execute(new CounterUserTask(counter, bound));
        }
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        Assertions.assertEquals(2*bound, counter.get());
    }


    /**
     * many thread with high consistency test
     * @param counter
     * @throws InterruptedException
     * @throws ExecutionException
     */
    void highContentionConsistencyTest(SharedCounter counter) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for(int i = 0; i<10; i++) {
            executor.execute(new CounterUserTask(counter, bound));
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        Assertions.assertEquals(5*bound, counter.get());
    }

}
