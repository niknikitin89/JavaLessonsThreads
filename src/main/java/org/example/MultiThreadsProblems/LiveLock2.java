package org.example.MultiThreadsProblems;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLock2 {

    public static void main(String[] args) {
        final Lock lock1 = new ReentrantLock();
        final Lock lock2 = new ReentrantLock();

        // Поток 1: пытается захватить оба ресурса, но уступает, если другой активен
        Thread thread1 = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Поток 1: пытается захватить lock1");
                    if (lock1.tryLock()) {
                        System.out.println("Поток 1: захватил lock1");
                        Thread.sleep(100); // Имитация работы
                        System.out.println("Поток 1: пытается захватить lock2");
                        if (lock2.tryLock()) {
                            System.out.println("Поток 1: захватил lock2 — успех!");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        } else {
                            System.out.println("Поток 1: не получил lock2, отпускает lock1");
                            Thread.sleep(100);
                            lock1.unlock(); // Уступает место
                        }
                    }
                    // Короткая пауза, чтобы дать шанс другому потоку
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Поток 2: делает то же самое, но в обратном порядке
        Thread thread2 = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Поток 2: пытается захватить lock2");
                    if (lock2.tryLock()) {
                        System.out.println("Поток 2: захватил lock2");
                        Thread.sleep(100);
                        System.out.println("Поток 2: пытается захватить lock1");
                        if (lock1.tryLock()) {
                            System.out.println("Поток 2: захватил lock1 — успех!");
                            lock1.unlock();
                            lock2.unlock();
                            break;
                        } else {
                            System.out.println("Поток 2: не получил lock1, отпускает lock2");
                            Thread.sleep(100);
                            lock2.unlock(); // Уступает место
                        }
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}
