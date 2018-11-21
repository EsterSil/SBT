import org.junit.jupiter.api.Test;
import sbt.edu.sharedcounter.combiningtree.CombiningTreeCounter;

public class CombiningTreeTest {

    private CommonCounterTest test = new CommonCounterTest(100);
    CombiningTreeCounter counter = new CombiningTreeCounter(11);

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
