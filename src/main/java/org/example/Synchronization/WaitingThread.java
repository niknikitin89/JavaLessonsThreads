package org.example.Synchronization;

public class WaitingThread {
    public static void main(String[] args) {
        Object lock = new Object();
        Runnable task = ()->{
            synchronized (lock) {
                try{
                    System.out.println("ChildThread get lock");
                    lock.wait();
                    System.out.println("ChildTread finished waiting");
                }catch (InterruptedException e){
                    System.out.println("Thread interrupted");
                }
            }
        };

        Thread thread = new Thread(task);
        thread.start();
        try{
            Thread.sleep(200);
        }catch (InterruptedException e){
            System.out.println("Thread interrupted");
        }

        synchronized (lock) {
            System.out.println("MainThread get lock");
            lock.notify();
            System.out.println("MainThread notify child");
        }
    }
}


