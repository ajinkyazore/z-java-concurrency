package com.training.zore.threadpools.app2;

import java.util.List;
import java.util.concurrent.*;

import org.junit.Assert;
import org.junit.Assert.*;

public class FixedThreadpoolExecutorApp {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        service.submit(() -> {
           Thread.sleep(1000);
           return "Hello";
        });

        service.submit(() -> {
            Thread.sleep(1000);
            return "Hello";
        });

        service.submit(() -> {
            Thread.sleep(1000);
            return "Hello";
        });

        Assert.assertEquals(2,service.getActiveCount());
        Assert.assertEquals(1,service.getQueue().size());

        System.out.println(service.getActiveCount());
        System.out.println(service.getQueue().size());

        service.awaitTermination(500, TimeUnit.MILLISECONDS);

        List<Runnable> unfinishedTasks = service.shutdownNow();

        System.out.println("Done");
    }
}
