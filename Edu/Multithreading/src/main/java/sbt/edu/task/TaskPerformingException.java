package sbt.edu.task;

public class TaskPerformingException extends RuntimeException {

    private final Throwable throwable;

    public TaskPerformingException(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
