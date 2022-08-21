package com.training.zore.waitnotify.app1;

public class Data {

    private String packet;

    public volatile boolean transfer = true;

    public synchronized String receive() {

        while(transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }

        transfer = true;
        notifyAll();
        return packet;
    }


    public synchronized void send(String packet) {
        while(!transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        }
        transfer = false;
        this.packet = packet;
        notifyAll();
    }
}
