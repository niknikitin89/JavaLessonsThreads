package org.example.SimpleThreadsCreation;

public class ThreadExtension2 {
    public static void main(String[] args) {

        try {
            MyThread2 myThread = new MyThread2();
            myThread.start();

            for (int i = 0; i < 10; i++) {
                System.out.println(". " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class MyThread2 extends Thread {
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




