package org.example.InterruptedThread;

import static java.lang.Thread.sleep;

public class ThreadInterruption {
    public static void main(String[] args) {
        Runnable task = () -> {
            while (!Thread.currentThread().isInterrupted()) {
            }
            System.out.println("Finished");
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.interrupt();
    }
}
