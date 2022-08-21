package com.training.zore.callablerunnable.app1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new EventLoggingTask());
        executorService.shutdown();
    }
}

class EventLoggingTask implements  Runnable{

    @Override
    public void run() {
        System.out.println("Message");
    }
}
