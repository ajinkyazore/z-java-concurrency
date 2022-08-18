package com.training.zore.sync.app1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import static org.junit.Assert.* ;

public class NonSynchronizedApp {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        NonSynchronized nonSynchronized = new NonSynchronized();

        IntStream.range(0, 1000).forEach(x -> service.submit(nonSynchronized::calculate));
        service.awaitTermination(10, TimeUnit.SECONDS);
        assertEquals(1000, nonSynchronized.getSum());



    }
}

class NonSynchronized {
    private int sum = 0;

    public void calculate() {
        setSum(getSum() + 1);
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
