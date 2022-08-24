package com.training.zore.threadpools.app5;

import org.junit.Assert;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomParametersThreadPoolApp {

    public static void main(String[] args) {
        ThreadPoolExecutor service = new ThreadPoolExecutor(2, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(11));
        assert 2 == service.getCorePoolSize();
        assert 10 == service.getMaximumPoolSize();
        assert 11 == service.getQueue().size();
        service.shutdown();
    }
}
