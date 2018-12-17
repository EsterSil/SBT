package sbt.edu.executionmanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExecutionManagerImplTest {


    @Test
     void contextWithCorrectTasksTest() {
        ExecutionManager manager = new ExecutionManagerImpl();

        Context context = manager.execute(successfulTask(1000, "Callable is over"), successfulTask(100, "Task 1 is over"), successfulTask(100, "Task 2 is over"));
        Assertions.assertEquals(context.getCompletedTaskCount(),0);
        while (!context.isFinished()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assertions.assertEquals(context.getCompletedTaskCount(),2);

    }

    @Test
    void contextWithExceptionalTaskTest(){
        ExecutionManager manager = new ExecutionManagerImpl();
        Context context = manager.execute(successfulTask(100, "Callable is over"),
                successfulTask(100, "Task 1 is over"),
                exceptionThrowingTask(),
                exceptionThrowingTask());
        Assertions.assertEquals(context.getCompletedTaskCount(),0);
        Assertions.assertEquals(context.getFailedTaskCount(),0);
        while (!context.isFinished()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assertions.assertEquals(context.getCompletedTaskCount(),1);
        Assertions.assertEquals(context.getFailedTaskCount(),2);

    }

    @Test
    void contextInterruptTest() {
        ExecutionManager manager = new ExecutionManagerImpl();

        Context context = manager.execute(successfulTask(1000, "Callable is over"),
                successfulTask(100, "Task 1 is over"),
                successfulTask(100, "Task 2 is over"));

        Assertions.assertEquals(context.getCompletedTaskCount(),0);
        Assertions.assertEquals(context.getFailedTaskCount(),0);
        Assertions.assertEquals(context.getInterruptedTaskCount(),0);
        context.interrupt();
        while (!context.isFinished()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assertions.assertEquals(context.getCompletedTaskCount(),0);
        Assertions.assertEquals(context.getFailedTaskCount(),0);
        Assertions.assertEquals(context.getInterruptedTaskCount(),2);

    }

    private Runnable exceptionThrowingTask() {
        return () ->{
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new IllegalArgumentException("task2");
        };
    }

    private Runnable successfulTask(int i, String s) {
        return () -> {
            try {
                Thread.sleep(i);
                System.out.println(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
