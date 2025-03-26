package org.example.SimpleThreadsCreation;

public class ThreadExtension2 {
    public static void main(String[] args) {
        try {
            MyThread myThread = new MyThread();
            myThread.start();

            for (int i = 0; i < 10; i++) {
                System.out.println(". " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class MyThread extends Thread {
        @Override
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



