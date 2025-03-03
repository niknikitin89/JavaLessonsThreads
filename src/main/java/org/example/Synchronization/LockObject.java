package org.example.Synchronization;

public class LockObject {
    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();

        Runnable task = () -> {
            synchronized (lock) {
                System.out.println("ChildThread working");
            }
        };

        Thread thread = new Thread(task);
        thread.start();
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                System.out.println("MainThread: " + i);
            }
            System.out.println("ChildTread status: " + thread.getState());
            System.out.println("MainThread finished");
        }

    }
}
