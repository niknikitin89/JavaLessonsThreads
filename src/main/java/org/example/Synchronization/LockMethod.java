package org.example.Synchronization;

import static java.lang.Thread.sleep;

public class LockMethod {
    public static void main(String[] args) throws InterruptedException {

        Runnable task = new Task();
        Thread thread0 = new Thread(task);
        Thread thread1 = new Thread(task);
        thread0.start();
        thread1.start();

        Thread.sleep(200);
        System.out.println(
                thread0.getName() + " status - " + thread0.getState());
        System.out.println(
                thread1.getName() + " status - " + thread1.getState());
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        synchMethod();
    }

    synchronized void synchMethod() {
        try {
            String name = Thread.currentThread().getName();
            System.out.println("Thread "+ name + " started");
            for (int i = 0; i < 5; i++) {
                System.out.println(name + " " + i);
                sleep(500);
            }

            System.out.println("Thread "+ name + " finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}