package org.example.ManyThreads;

import static java.lang.Thread.sleep;

public class ManyThreadsJoin {
    public static void main(String[] args) {
        System.out.println("MainThread started");

        MyThread myThread1 = MyThread.createAndStart("Child 1");
        MyThread myThread2 = MyThread.createAndStart("Child 2");
        MyThread myThread3 = MyThread.createAndStart("Child 3");

        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("MainThread -> " + i);
                sleep(100);
            }
        } catch (InterruptedException ex) {
            System.out.println("MainThread interrupted");
        }

        try {
            myThread1.thread.join();//Ждать завершения указанного потока
            System.out.println(myThread1.thread.getName() + " joined");
            myThread2.thread.join();//Ждать завершения указанного потока
            System.out.println(myThread2.thread.getName() + " joined");
            myThread3.thread.join();//Ждать завершения указанного потока
            System.out.println(myThread3.thread.getName() + " joined");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("MainThread finished");
    }
}


