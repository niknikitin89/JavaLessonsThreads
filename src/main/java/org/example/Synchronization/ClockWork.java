package org.example.Synchronization;

public class ClockWork {
    public static void main(String[] args) {
        Clock clock = new Clock();
        Task tickTask = new Task("Tick", clock);
        Task tackTask = new Task("Tack", clock);
        Thread tickThread = new Thread(tickTask);
        Thread tackThread = new Thread(tackTask);
        tickThread.start();
        tackThread.start();

        try {
            tickThread.join();
            tackThread.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    static class Clock {
        private final int TICKED = 1;
        private final int TACKED = 2;
        private int state; //состояние часов

        synchronized void tick(boolean running) {
            if (!running) { //останавливаем часы
                state = TICKED;
                notify(); //уведомить ожидающие потоки
                return;
            }
            System.out.println("Tick ");
            state = TICKED;//устанавливаем текущее состояние часов
            notify();//оповестить ожидающие потоки
            try {
                while (state == TICKED) {
                    wait();//ожидаем завершение другого потока
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }

        synchronized void tack(boolean running) {
            if (!running) { //останавливаем часы
                state = TACKED;
                notify(); //уведомить ожидающие потоки
                return;
            }
            System.out.println("Tack");
            state = TACKED;//устанавливаем текущее состояние часов
            notify();//оповестить ожидающие потоки
            try {
                while (state == TACKED) {
                    wait();//ожидаем завершение другого потока
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }

    static class Task implements Runnable {
        private Clock clock;
        private String taskName;

        public Task(String taskName, Clock clock) {
            this.clock = clock;
            this.taskName = taskName;
        }

        @Override
        public void run() {
            if (taskName.equals("Tick")) {
                for (int i = 0; i < 5; i++) {
                    clock.tick(true);
                }
                clock.tick(false);
            } else {
                for (int i = 0; i < 5; i++) {
                    clock.tack(true);
                }
                clock.tack(false);
            }
        }
    }
}



