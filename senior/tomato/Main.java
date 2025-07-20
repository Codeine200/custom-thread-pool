package senior.tomato;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        MyCustomThreadPool pool = new MyCustomThreadPool(2);
        pool.submit(() -> {
            System.out.println("Start Task 1");
            sleep(1500);
            System.out.println("End Task 1");
        });

        pool.submit(() -> {
            System.out.println("Start Task 2");
            sleep(1500);
            System.out.println("End Task 2");
        });

        pool.submit(() -> {
            System.out.println("Start Task 3");
            sleep(500);
            System.out.println("End Task 3");
        });
        pool.shutdownNow();

        sleep(2000);
        System.exit(0);

        Thread task1 = new Thread(() -> {
            System.out.println("Start Task 1");
            sleep(1500);
            System.out.println("End Task 1");
        });

        Thread task2 = new Thread(() -> {
            System.out.println("Start Task 2");
            sleep(1500);
            System.out.println("End Task 2");
        });

        Thread task3 = new Thread(() -> {
            System.out.println("Start Task 3");
            sleep(1500);
            System.out.println("End Task 3");
        });

        task1.start();
        task2.start();
        task3.start();

        sleep(2000);

        System.exit(0);

        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {
            System.out.println("Start Task 1");
            sleep(1500);
            System.out.println("End Task 1");
        });

        executor.execute(() -> {
            System.out.println("Start Task 2");
            sleep(1000);
            System.out.println("End Task 2");
        });
        executor.execute(() -> {
            System.out.println("Start Task 3");
            sleep(10);
            System.out.println("End Task 3");
        });
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
