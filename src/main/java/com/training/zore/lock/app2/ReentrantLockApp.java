package com.training.zore.lock.app2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockApp {

    public static void main(String[] args) {

    }
}

class SharedObject {
    ReentrantLock lock = new ReentrantLock();
    int counter = 0;

    public void performLock() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public void performTryLock() throws InterruptedException {
        boolean isLockAcquired = lock.tryLock(1, TimeUnit.SECONDS);
        if (isLockAcquired) {
            try {

            } finally {
                lock.unlock();
            }
        }
    }
}
