package org.example.SimpleThreadsCreation;

public class ThreadExtension1 {
    public static void main(String[] args) {

        try {
            MyThread myThread = new MyThread();
            myThread.start();
            System.out.println("Main thread: " + myThread.getName() + " started");
            Thread.sleep(1000);
            System.out.println("Main thread: finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                sleep(500);
                System.out.println("....Work hard!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}






