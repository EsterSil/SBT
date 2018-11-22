package sbt.edu.sharedcounter.syncronizedcounter;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Group)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SyncCounterBenchmark {


    private final static SyncCounter counter = new SyncCounter();



    @Benchmark

    public void oneThread() {
        int result = 0;
        for (int i = 0; i < 10000000; i++) {
            result = counter.getAndIncrement();
        }


    }

    @Benchmark
    @Group("twoThreadTest")
    public void twoThreadsFirst(int bound) {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
    }
    @Benchmark
    @Group("twoThreadTest")
    public void twoThreadsSecond(int bound) {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
    }


    @Benchmark
    @Group("threeThreadTest")
    public void threeThreadsFirst(int bound) {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
    }

    @Benchmark
    @Group("threeThreadTest")
    public void threeThreadsSecond(int bound) {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
    }

    @Benchmark
    @Group("threeThreadTest")
    public void threeThreadsThird(int bound) {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
    }
}
