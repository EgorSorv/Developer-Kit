package hw5;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread {
    private final Table table;
    private final String name;
    private final int leftFork;
    private final int rightFork;
    private final Random random;
    private final CountDownLatch countDownLatch;
    private int count;

    public Philosopher(Table table, String name, int leftFork, int rightFork, CountDownLatch countDownLatch) {
        this.table = table;
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.countDownLatch = countDownLatch;
        random = new Random();
        count = 0;
    }

    @Override
    public void run() {
        while (count < 3) {
            try {
                thinking();
                eating();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(name + " is full");
        countDownLatch.countDown();
    }

    private void eating() throws InterruptedException {
        if (table.getForks(leftFork, rightFork)) {
            System.out.println(name + " is having lunch using forks " + (leftFork + 1) + " and " +
                    (rightFork + 1));
            sleep(random.nextLong(3000, 6000));
            table.putForks(leftFork, rightFork);
            System.out.println(name + " has finished dinner and put forks " + (leftFork + 1) + " and " +
                    (rightFork + 1) + " on the table. He's thinking now");
            count++;
        }
    }

    private void thinking() throws InterruptedException {
        sleep(random.nextLong(100, 2000));
    }
}
