package com.training.zore.threadpools.app4;

import org.junit.Assert;

import java.sql.Time;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadpoolExecutorApp {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

        service.schedule(() -> {
           System.out.println("Thread running at one delay");
           return "Hello";
        }, 500, TimeUnit.MILLISECONDS);

        service.scheduleAtFixedRate(() -> {
            System.out.println("Thread running at fixed Rate initialDelay 500ms schedule 100ms");
        }, 1000, 2000, TimeUnit.MILLISECONDS);

        service.scheduleWithFixedDelay(() -> {
            System.out.println("Thread running at fixed Rate initialDelay 1000ms delay 500ms");
        }, 1000, 5000, TimeUnit.MILLISECONDS);

        System.out.println("Done");
    }
}
