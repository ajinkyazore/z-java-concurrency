package com.training.zore.waitnotify.app1;

import java.util.concurrent.ThreadLocalRandom;

public class Receiver implements Runnable{
    private Data data;

    public Receiver(Data data) {
        this.data = data;
    }

    @Override
    public void run() {

        for(String receivedMessage = data.receive(); !receivedMessage.equals("End"); receivedMessage = data.receive()) {

            System.out.println(receivedMessage);

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
