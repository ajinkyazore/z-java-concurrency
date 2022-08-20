package com.training.zore.threadpools.app3;

import org.junit.Assert;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CachedThreadpoolExecutorApp {
    public static void main(String[] args) {
        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newCachedThreadPool();

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

        Assert.assertEquals(3,service.getActiveCount());
        Assert.assertEquals(0,service.getQueue().size());

        System.out.println(service.getActiveCount());
        System.out.println(service.getQueue().size());
        System.out.println("Done");
    }
}
