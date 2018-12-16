package sbt.edu.executionmanager;

public class MainExec {

    public static void main(String[] args) {
        ExecutionManager manager = new ExecutionManagerImpl();

        Context context = manager.execute(() ->{
            try {
                Thread.sleep(1000);
                System.out.println("Callable is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, () ->{
            try {
                Thread.sleep(100);
                System.out.println("Task1 is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, () ->{
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new IllegalArgumentException("task2");
        });

        System.out.println(context.getCompletedTaskCount()+ " " + context.getFailedTaskCount());
        context.interrupt();
        while (!context.isFinished()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(context.getCompletedTaskCount()+ " " + context.getFailedTaskCount()+ " "+ context.getInterruptedTaskCount());
    }
}
