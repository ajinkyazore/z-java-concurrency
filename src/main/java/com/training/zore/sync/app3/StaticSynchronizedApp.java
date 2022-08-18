package com.training.zore.sync.app3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class StaticSynchronizedApp {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);

        IntStream.range(0, 1000).forEach(x -> service.submit(StaticSynchronized::calculate));
        service.awaitTermination(10, TimeUnit.SECONDS);
        assertEquals(1000, StaticSynchronized.getSum());
        System.out.println("Complete");

        service.shutdown();
    }


}

class StaticSynchronized {
    private static int sum = 0;

    public static synchronized void calculate() {
        setSum(getSum() + 1);
    }

    public static int getSum() {
        return sum;
    }

    public static void setSum(int x) {
        sum = x;
    }
}
