package org.example.MultiThreadsProblems;

public class Starvation2 {

    private static final Object lock = new Object();
    private static int counter = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new FastTask(), "FastThread-" + i).start();
        }

        new Thread(new SlowTask(), "SlowThread").start();
    }

    static class FastTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    counter++;
                    System.out.println(Thread.currentThread().getName() + ": " + counter);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class SlowTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    counter++;
                    System.out.println("SlowThread: " + counter);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

