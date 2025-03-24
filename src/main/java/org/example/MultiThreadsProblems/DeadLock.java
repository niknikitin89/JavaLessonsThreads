package org.example.MultiThreadsProblems;

public class DeadLock {
    public static void main(String[] args) {
        final Object lock1 = new Object();
        final Object lock2 = new Object();

        // Поток 1: захватывает lock1, затем пытается взять lock2
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Поток 1: удерживает lock1");
                try {
                    Thread.sleep(100); // Имитация работы
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток 1: ждёт lock2...");
                synchronized (lock2) {
                    System.out.println("Поток 1: удерживает lock1 и lock2");
                }
            }
        });

        // Поток 2: захватывает lock2, затем пытается взять lock1
        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Поток 2: удерживает lock2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток 2: ждёт lock1...");
                synchronized (lock1) {
                    System.out.println("Поток 2: удерживает lock2 и lock1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}


