package org.example.MultiThreadsProblems;

public class Starvation {

    private static final Object sharedResource = new Object();

    public static void main(String[] args) {
        // Жадный поток с высоким приоритетом
        Thread greedyThread = new Thread(() -> {
            while (true) {
                synchronized (sharedResource) {
                    System.out.println("Жадный поток работает...");
                    try {
                        Thread.sleep(100); // Имитация работы
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        greedyThread.setPriority(Thread.MAX_PRIORITY); // Максимальный приоритет

        // Скромный поток с низким приоритетом
        Thread humbleThread = new Thread(() -> {
            while (true) {
                synchronized (sharedResource) {
                    System.out.println("Скромный поток наконец-то получил доступ!");
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        humbleThread.setPriority(Thread.MIN_PRIORITY); // Минимальный приоритет

        greedyThread.start();
        humbleThread.start();
    }
}
