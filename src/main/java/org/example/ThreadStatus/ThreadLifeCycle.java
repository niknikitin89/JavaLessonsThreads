package org.example.ThreadStatus;

public class ThreadLifeCycle {
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();

            System.out.println(
                    "Состояние потока после создания: " +
                            thread.getState());  // NEW
            System.out.println("Живой? " + thread.isAlive());
            thread.start();
            System.out.println(
                    "Состояние потока после вызова start(): " +
                            thread.getState());  // RUNNABLE
            System.out.println("Живой? " + thread.isAlive());

            thread.setTimeToSleep(true);
            //Остановим основной поток, чтобы дочерний успел заснуть
            Thread.sleep(300);
            System.out.println(
                    "Состояние потока после вызова sleep(): " +
                            thread.getState());  // TIMED_WAITING
            System.out.println("Живой? " + thread.isAlive());

            //Остановим основной поток до завершения дочернего
            Thread.sleep(600);

            System.out.println(
                    "Состояние потока после завершения: " +
                            thread.getState());  // TERMINATED
            System.out.println("Живой? " + thread.isAlive());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class MyThread extends Thread {
    private boolean timeToSleep = false;

    public void setTimeToSleep(boolean timeToSleep) {
        this.timeToSleep = timeToSleep;
    }

    @Override
    public void run() {
        System.out.println("Мы находимся в методе 'run'.");
        while (!timeToSleep) {
            ;
        }
        try {
            sleep(500);// Переход потока в TIMED_WAITING
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

