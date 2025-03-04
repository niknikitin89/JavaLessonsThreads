package org.example.InterruptedThread;

import static java.lang.Thread.sleep;

public class InterruptException {
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                sleep(500);
                while (!Thread.currentThread().isInterrupted()) {
                }
                System.out.println("Finished");
            } catch (InterruptedException e) {
                System.out.println("Exception called");
            }
        };

        Thread thread = new Thread(task);
        thread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }
        thread.interrupt();
    }
}
