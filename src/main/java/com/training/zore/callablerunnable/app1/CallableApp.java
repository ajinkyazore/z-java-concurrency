package com.training.zore.callablerunnable.app1;

import java.util.concurrent.*;

public class CallableApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new FactorialTask(5));
        while(!future.isDone()){
            Thread.yield();
        }
        try {
            System.out.println(future.get());
            System.out.println(future.isDone());
            System.out.println(future.isCancelled());
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(future.isDone());
            System.out.println(future.isCancelled());
        }
        executorService.shutdown();
    }
}

class FactorialTask implements Callable<Integer> {
    public Integer number;

    FactorialTask(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        return number / 0;
    }
}

