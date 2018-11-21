import org.junit.jupiter.api.Test;
import sbt.edu.sharedcounter.syncronizedcounter.SyncCounter;


public class SyncCounterTest {
    private SyncCounter counter = new SyncCounter();
    private CommonCounterTest test = new CommonCounterTest(1000000);


    @Test
    void singleThreadTest() {
        try {
            test.consistencyTest(counter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void twoThreadsTest() {
        try {
            test.lowContentionConsistencyTest(counter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void manyThreadTest() {
        try {
            test.highContentionConsistencyTest(counter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
