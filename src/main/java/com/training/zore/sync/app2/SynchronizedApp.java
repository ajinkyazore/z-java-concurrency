package com.training.zore.sync.app2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class SynchronizedApp {
    public static void main(String[] args) throws InterruptedException {
        //synchronizedExample();
        synchronizedThisExample();
    }

    private static void synchronizedExample() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        TrulySynchronized trulySynchronized = new TrulySynchronized();

        IntStream.range(0, 1000).forEach(x -> service.submit(trulySynchronized::calculate));
        service.awaitTermination(10, TimeUnit.SECONDS);
        assertEquals(1000, trulySynchronized.getSum());
        System.out.println("Complete");

        service.shutdown();
    }

    private static void synchronizedThisExample() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        TrulySynchronized trulySynchronized = new TrulySynchronized();

        IntStream.range(0, 1000).forEach(x -> service.submit(trulySynchronized::thisCalculate));
        service.awaitTermination(10, TimeUnit.SECONDS);
        assertEquals(1000, trulySynchronized.getSum());
        System.out.println("Complete");

        service.shutdown();
    }


}

class TrulySynchronized {
    private int sum = 0;

    public synchronized void calculate() {
        setSum(getSum() + 1);
    }

    public void thisCalculate() {
        synchronized (this) {
            setSum(getSum() + 1);
        }
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
