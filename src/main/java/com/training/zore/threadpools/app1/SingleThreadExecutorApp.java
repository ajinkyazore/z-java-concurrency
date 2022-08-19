package com.training.zore.threadpools.app1;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SingleThreadExecutorApp {
    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("Task run"));
    }
}
