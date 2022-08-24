package com.training.zore.forkjoin.app1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinPoolBasicApp
{
    public static void main(String[] args) {
        System.out.println(getSum(1_000_000));
    }

    public static long getSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        MyForkJoinTask task = new MyForkJoinTask(numbers);
        return new ForkJoinPool().invoke(task);
    }
}

class MyForkJoinTask extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long threshold = 10_000;

    public MyForkJoinTask(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private MyForkJoinTask(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    private long add() {
        long result = 0;
        for (int i = start; i < end; i++) {
            result += numbers[i];
        }
        return result;
    }

    @Override
    protected Long compute() {
        int length = end-start;
        if(length <= threshold) {
            return add();
        }

        MyForkJoinTask firstTask = new MyForkJoinTask(numbers, start, start+length /2);
        firstTask.fork();

        MyForkJoinTask secondTask = new MyForkJoinTask(numbers, start+length /2, end);
        secondTask.fork();

        Long firstTaskResult = firstTask.join();
        Long secondTaskResult = secondTask.join();

        return firstTaskResult + secondTaskResult;


    }
}
