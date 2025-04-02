package org.example.Daemon;

public class Daemon {

    public static void main(String[] args) {
        Thread userThread = new Thread(() -> {
            System.out.println("Обычный поток начал работу.");
            try {
                Thread.sleep(3000); // Имитация работы
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Обычный поток завершился.");
        });

        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Демон работает в фоне...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        daemonThread.setDaemon(true); // Делаем поток демоном

        userThread.start();
        daemonThread.start();

        System.out.println("Main-поток завершился.");
    }
}

