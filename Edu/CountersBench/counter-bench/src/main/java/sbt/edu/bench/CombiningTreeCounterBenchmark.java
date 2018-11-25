package sbt.edu.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import sbt.edu.sharedcounter.SharedCounter;
import sbt.edu.sharedcounter.combiningtree.CombiningTreeCounter;
import sbt.edu.sharedcounter.concurrentcounter.ConCounter;

import java.util.concurrent.TimeUnit;

@State(Scope.Group)
@OutputTimeUnit(TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
public class CombiningTreeCounterBenchmark {


    private final static SharedCounter counter = new CombiningTreeCounter(20);
    @Param({"10000", "100000", "1000000"})
    private int bound;


    @Benchmark
    @Group("oneThreadTest")
    @GroupThreads(value = 1)
    public void oneThread() throws Exception {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }


    }

    @Benchmark
    @Group("twoThreadTest")
    @GroupThreads(value = 2)
    public void twoThreads() throws Exception {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
    }


    @Benchmark
    @Group("fourThreadTest")
    @GroupThreads(value = 4)
    public void fourThreads() throws Exception {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
    }

    @Benchmark
    @Group("eightThreadTest")
    @GroupThreads(value = 8)
    public void eightThreads() throws Exception {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
    }

    @Benchmark
    @Group("sixteenThreadTest")
    @GroupThreads(value = 16)
    public void sixteenThreads() throws Exception {
        int result = 0;
        for (int i = 0; i < bound; i++) {
            result = counter.getAndIncrement();
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(CombiningTreeCounterBenchmark.class.getSimpleName())
                .include((ConCounterBenchmark.class.getSimpleName()))
                .include((SyncCounterBenchmark.class.getSimpleName()))
                .warmupIterations(2)
                .measurementIterations(5)
                .forks(1).build();

        new Runner(opt).run();

    }

}
