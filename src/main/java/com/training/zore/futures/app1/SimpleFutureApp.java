package com.training.zore.futures.app1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SimpleFutureApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //simpleFutureProcess();
        cancelledFutureProcess();
    }

    private static void cancelledFutureProcess() throws InterruptedException, ExecutionException {
        FormulaCalculator fc = new FormulaCalculator();

        Future<Integer> resultFuture = fc.calculateFast(10);
        System.out.println("resultFuture assigned future object");
        Thread.sleep(5);
        boolean cancelled = resultFuture.cancel(true);
        System.out.println("Future is cancelled !!!");

        System.out.println("Cancelled "+ resultFuture.isCancelled());
        System.out.println("Done "+resultFuture.isDone());
    }

    private static void simpleFutureProcess() throws InterruptedException, ExecutionException {
        FormulaCalculator fc = new FormulaCalculator();

        Future<Integer> resultFuture = fc.calculate(10);
        System.out.println("resultFuture assigned future object. Now starting while loop");
        while(!resultFuture.isDone()){
            System.out.println("Checking if future done");
            Thread.sleep(5);
        }
        System.out.println("Future is completed !!!");

        System.out.println("Future value : "+resultFuture.get());

        System.out.println("Cancelled "+ resultFuture.isCancelled());
        System.out.println("Done "+resultFuture.isDone());
    }
}



class FormulaCalculator {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public Future<Integer> calculate(Integer x) {
        return executorService.submit(() -> {
            System.out.println("Calling thread sleep");
            Thread.sleep(1000);
            System.out.println("Thread woken up");
            return x*x;
        });
    }

    public Future<Integer> calculateFast(Integer x) {
        return executorService.submit(() -> {
            System.out.println("Calling thread sleep");
            Thread.sleep(3);
            System.out.println("Thread woken up");
            return x*x;
        });
    }
}
