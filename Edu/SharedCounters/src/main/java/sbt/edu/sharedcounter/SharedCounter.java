package sbt.edu.sharedcounter;

public interface SharedCounter {

    long get();
    long getAndIncrement() throws Exception;
}
