package com.training.zore.threadpools.app2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Assert;
import org.junit.Assert.*;

public class FixedThreadpoolExecutorApp {
    public static void main(String[] args) {
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
        System.out.println("Done");
    }
}
