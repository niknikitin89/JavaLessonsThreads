package org.example.ManyThreads;

import static java.lang.Thread.sleep;

public class ManyThreads {
    public static void main(String[] args) {
        System.out.println("MainThread started");

        MyThread myThread1 = MyThread.createAndStart("Child 1");
        MyThread myThread2 = MyThread.createAndStart("Child 2");
        MyThread myThread3 = MyThread.createAndStart("Child 3");

        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("MainThread -> " + i);
                sleep(100);
            }
        } catch (InterruptedException ex) {
            System.out.println("MainThread interrupted");
        }
        System.out.println("MainThread finished");
    }
}

class MyThread implements Runnable {
    //Ссылка на объект потока
    private Thread thread;

    MyThread(String name) {
        //инициализация потока
        thread = new Thread(this, name);
    }

    //Фабричный метод, который создает поток и сразу его запускает
    public static MyThread createAndStart(String name) {
        MyThread myThread = new MyThread(name);
        myThread.thread.start();
        return myThread;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread " + thread.getName() + " started");
            for (int i = 0; i < 10; i++) {
                System.out.println("- Thread " + thread.getName() + " -> " + i);
                sleep(100);
            }
        } catch (InterruptedException ex) {
            System.out.println("Thread " + thread.getName() + " interrupted");
        }
    }
}


