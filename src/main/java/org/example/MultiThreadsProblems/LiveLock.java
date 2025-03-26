package org.example.MultiThreadsProblems;

public class LiveLock {

    public static void main(String[] args) {
        final Person alice = new Person("Алиса", true);
        final Person bob = new Person("Боб", true);

        // Поток для Алисы
        new Thread(() -> alice.tryToPass(bob)).start();
        // Поток для Боба
        new Thread(() -> bob.tryToPass(alice)).start();
    }

    static class Person {
        private String name;
        private boolean isPolite;

        public Person(String name, boolean isPolite) {
            this.name = name;
            this.isPolite = isPolite;
        }

        public void tryToPass(Person other) {
            while (true) {
                if (this.isPolite) {
                    System.out.println(name + ": «После вас, " + other.name + "!»");
//                    this.isPolite = false; // Перестаёт быть вежливым
                    try {
                        Thread.sleep(1000); // Пауза для наглядности
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(name + ": проходит!");
                    break;
                }
            }
        }
    }
}
