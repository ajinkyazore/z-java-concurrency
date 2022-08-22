package com.training.zore.mutex.app1;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReEntrantMutexApp {

    public static void main(String[] args) throws Exception {
        ReEntrantMutexApp reEntrantMutexApp = new ReEntrantMutexApp();
        int count = 1000;
        Assert.assertEquals(1000, reEntrantMutexApp.getUniqueSequences(new ReentrantSequenceGenerator(), count).size());
    }

    private Set<Integer> getUniqueSequences(SequenceGenerator sequenceGenerator, int count) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Set<Integer> uniqueSequenceSet = new LinkedHashSet<>();
        List<Future<Integer>> futureList = new ArrayList<>();

        IntStream.range(0, count).forEach(x -> futureList.add(executorService.submit(sequenceGenerator::getNextSequence)));

        futureList.stream().forEach(x -> {
            try {
                uniqueSequenceSet.add(x.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
        executorService.shutdown();

        return uniqueSequenceSet;



    }
}

class ReentrantSequenceGenerator extends SequenceGenerator {
    ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public int getNextSequence() {
        try {
            reentrantLock.lock();
            return super.getNextSequence();
        } finally {
            reentrantLock.unlock();
        }
    }
}
