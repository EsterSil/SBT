package sbt.edu.task;

import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {

        Callable<Integer> callable = () -> {
            System.out.println(Thread.currentThread().getName() + " counting");
            Thread.sleep(3000);
            //throw new IllegalArgumentException(Thread.currentThread().getName());
            return 100;
        };

        Task<Integer> task = new TaskImpl<>(callable);
        for (int i = 0; i < 5; i++) {

            Thread t = new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " " + task.get());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.start();
        }


    }
}
