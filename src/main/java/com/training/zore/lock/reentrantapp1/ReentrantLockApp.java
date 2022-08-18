package com.training.zore.lock.reentrantapp1;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class ReentrantLockApp {
    private static Object lock = new Object();

    private int criticalSection() {
        synchronized (lock) {
            System.out.println("First time acquiring it");

            synchronized (lock) {
                System.out.println("Entering again");

                synchronized (lock) {
                    System.out.println("And again");
                }
            }
        }
        return 1;
    }

    private int criticalSectionWithoutSynchronized() throws InterruptedException {
            System.out.println("First time acquiring it");
            System.out.println("Entering again");
            System.out.println("And again");
            Thread.sleep(10);
        return 1;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        ReentrantLockApp lockApp = new ReentrantLockApp();

        IntStream.range(0, 1000).forEach(x -> service.submit(new ReentrantLockApp()::criticalSectionWithoutSynchronized));
        service.awaitTermination(10, TimeUnit.SECONDS);
    }
}
