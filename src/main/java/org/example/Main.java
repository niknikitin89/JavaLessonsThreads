package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Runnable task = new Task();
        Thread thread0 = new Thread(task);
        Thread thread1 = new Thread(task);
        thread0.start();
        thread1.start();
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        synchMethod();
    }
    synchronized void synchMethod() {
        System.out.println("Hello World");
    }
}


