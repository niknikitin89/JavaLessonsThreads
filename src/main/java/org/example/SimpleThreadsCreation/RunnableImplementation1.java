package org.example.SimpleThreadsCreation;

import static java.lang.Thread.sleep;

public class RunnableImplementation1 {
    public static void main(String[] args) {
        try {
            MyRunnable myRunnable = new MyRunnable();
            Thread thread = new Thread(myRunnable);
            thread.start();

            for (int i = 0; i < 10; i++) {
                System.out.println(". " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class MyRunnable implements Runnable {
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("... " + i);
                    sleep(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}




