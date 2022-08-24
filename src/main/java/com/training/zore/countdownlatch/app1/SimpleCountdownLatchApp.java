package com.training.zore.countdownlatch.app1;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SimpleCountdownLatchApp {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(10);

        IntStream.rangeClosed(1,20).forEach(x -> service.submit(new MyThread(countDownLatch)));

        System.out.println("All tasks submitted.");
        countDownLatch.await();
        System.out.println("5 tasks completed");
        System.out.println("Shutting down executor service");
        List<Runnable> nonExecutedTasks = service.shutdownNow();
        System.out.println("Collected all unfinished tasks and running them in same thread");
        nonExecutedTasks.forEach(x -> x.run());
    }
}

class MyThread extends Thread {

    private CountDownLatch countDownLatch;

    MyThread(CountDownLatch countDownLatch) {
       this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getId()+" thread execution start");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Countdown Latch Count"+countDownLatch.getCount());
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getId()+" thread execution end");
        }

    }



}
