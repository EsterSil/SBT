package sbt.edu.sharedcounter;

public interface SharedCounter {

    int get();

    int getAndIncrement() throws Exception;

    void reset();
}
