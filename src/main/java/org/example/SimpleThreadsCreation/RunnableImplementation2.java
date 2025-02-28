package org.example.SimpleThreadsCreation;

public class RunnableImplementation2 {
    public static void main(String[] args) {

        Thread thread = new Thread(
                () -> {
                    System.out.println("Hello from Runnable");
                });

        thread.start();
    }
}



