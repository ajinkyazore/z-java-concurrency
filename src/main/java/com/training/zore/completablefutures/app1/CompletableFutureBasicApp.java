package com.training.zore.completablefutures.app1;

import org.junit.Assert;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureBasicApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        supply();
        supplyThenApply();
        supplyThenAccept();
        supplyThenRun();
        supplyThenCompose();
        supplyAndCombine();
        waitForAll();
    }

    private static void waitForAll() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        combinedFuture.get();

        Assert.assertTrue(future1.isDone());
        Assert.assertTrue(future2.isDone());
        Assert.assertTrue(future3.isDone());

    }

    private static void supplyAndCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> completableFutureFinal = completableFuture.thenCombine(CompletableFuture.supplyAsync(() -> " Zizou"), (s1, s2) -> s1+s2);
        Assert.assertEquals("Hello Zizou", completableFutureFinal.get());
    }

    private static void supplyThenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture completableFutureFinal = completableFuture.thenCompose(s -> CompletableFuture.supplyAsync(() -> s+" World"));
        Assert.assertEquals("Hello World", completableFutureFinal.get());
    }

    private static void supplyThenRun() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture completableFutureFinal = completableFuture.thenRun(() -> System.out.println("World"));
        completableFutureFinal.get();
    }

    private static void supplyThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture completableFutureFinal = completableFuture.thenApply(s -> s+" World");
        Assert.assertEquals("Hello World", completableFutureFinal.get());
    }

    private static void supplyThenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        completableFuture.thenAccept(s ->System.out.println(s));
        completableFuture.get();
    }

    private static void supply() throws InterruptedException, ExecutionException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> "Hello World");
        Assert.assertEquals("Hello World", completableFuture.get());
    }
}
