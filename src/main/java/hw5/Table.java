package hw5;

import java.util.concurrent.CountDownLatch;

public class Table extends Thread {
    private final int PHILOSOPHER_COUNT = 5;
    private final Fork[] forks;
    private final Philosopher[] philosophers;
    private final CountDownLatch countDownLatch;


    public Table() {
        forks = new Fork[PHILOSOPHER_COUNT];
        philosophers = new Philosopher[PHILOSOPHER_COUNT];
        countDownLatch = new CountDownLatch(PHILOSOPHER_COUNT);

        for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
            philosophers[i] = new Philosopher(this, "Philosopher №" + (i + 1),
                    i, (i + 1) % PHILOSOPHER_COUNT, countDownLatch);
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Start lunch\n");
            lunch();
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nLunch is over");
    }

    public synchronized boolean getForks(int leftFork, int rightFork) {
        if (forks[leftFork].isUsing() && forks[rightFork].isUsing()) {
            forks[leftFork].setUsing(true);
            forks[rightFork].setUsing(true);
            return true;
        }

        return false;
    }

    public void putForks(int leftFork, int rightFork) {
        forks[leftFork].setUsing(false);
        forks[rightFork].setUsing(false);
    }

    private void lunch() {
        for (Philosopher philosopher: philosophers)
            philosopher.start();
    }
}
