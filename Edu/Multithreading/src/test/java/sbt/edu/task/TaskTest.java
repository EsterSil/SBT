package sbt.edu.task;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskTest {

    @Test
    void taskTest() {

        Callable<Integer> callable = () -> {
            System.out.println(Thread.currentThread().getName() + " counting");
            Thread.sleep(3000);
            //throw new IllegalArgumentException(Thread.currentThread().getName());
            return 100;
        };

        TaskImpl<Integer> task = new TaskImpl<>(callable);
        ExecutorService service = Executors.newFixedThreadPool(5);
        CountDownLatch counter = new CountDownLatch(5);
        for (int i = 0; i <5 ; i++) {
            service.execute(() -> {
                try {
                    System.out.println(task.get());
                    counter.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            counter.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //service.shutdown();

    }

}
