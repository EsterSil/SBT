package sbt.edu.sharedcounter.syncronizedcounter;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Group)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SyncCounterBenchmark {


    private final static SyncCounter counter = new SyncCounter();



    @Benchmark
    public int oneThread(int bound) {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
        return result;

    }

    @Benchmark
    @Group("twoThreadTest")
    public int twoThreadsFirst(int bound) {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
        return result;
    }
    @Benchmark
    @Group("twoThreadTest")
    public int twoThreadsSecond(int bound) {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
        return result;
    }


    @Benchmark
    @Group("threeThreadTest")
    public int threeThreadsFirst(int bound) {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
        return result;
    }

    @Benchmark
    @Group("threeThreadTest")
    public int threeThreadsSecond(int bound) {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
        return result;
    }

    @Benchmark
    @Group("threeThreadTest")
    public int threeThreadsThird(int bound) {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
        return result;
    }
}
