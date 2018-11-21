import org.junit.jupiter.api.Test;
import sbt.edu.sharedcounter.concurrentcounter.ConCounter;

public class ConCounterTest {

    private ConCounter conCounter = new ConCounter();
private CommonCounterTest test = new CommonCounterTest(1000000);

    @Test
    void singleThreadTest() {
        try {
            test.consistencyTest(conCounter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void twoThreadsTest() {
        try {
            test.lowContentionConsistencyTest(conCounter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void manyThreadTest() {
        try {
            test.highContentionConsistencyTest(conCounter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
