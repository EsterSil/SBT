package sbt.edu.task;

public interface Task<T> {

    T get() throws Exception;
}
