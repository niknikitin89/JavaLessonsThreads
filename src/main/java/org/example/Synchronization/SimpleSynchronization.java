package org.example.Synchronization;

import static java.lang.Thread.sleep;

public class SimpleSynchronization {
    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();

        Runnable task = () -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println(
                                Thread.currentThread().getName() + "-" + i);
                        sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread thread1 = new Thread(task,"Thread1");
        Thread thread2 = new Thread(task,"Thread2");
        thread1.start();
        thread2.start();
    }
}
