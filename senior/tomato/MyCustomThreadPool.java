package senior.tomato;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyCustomThreadPool {
    private List<Worker> workers = new ArrayList<>();
    private final List<Runnable> tasks = new LinkedList<>();
    private boolean isShutdown = false;

    public MyCustomThreadPool(int poolSize) {
        for (int i=0; i<poolSize; i++) {
            Worker worker = new Worker("thread#" + (i + 1));
            workers.add(worker);
            worker.start();
        }
    }

    public void submit(Runnable task) {
        synchronized (tasks) {
            tasks.addLast(task);
            tasks.notify();
        }
    }

    public void shutdownNow() {
        isShutdown = true;
        synchronized (tasks) {
            tasks.notifyAll(); // разбудим все потоки
        }
        for (Worker worker : workers) {
            worker.interrupt();
        }
    }

    class Worker extends Thread {

        public Worker(String name) {
            super(name);
        }

        @Override
        public void run () {
            while(true) {
                Runnable task;
                synchronized (tasks) {
                    while (tasks.isEmpty() && !isShutdown) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    if (isShutdown && tasks.isEmpty()) {
                        return;
                    }
                    task = tasks.removeFirst();
                }
                try {
                    System.out.println(getName() + " works");
                    task.run();
                } catch (Exception e) {
                    System.out.println("Error");
                }
            }
        }
    }
}
