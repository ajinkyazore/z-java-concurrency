package com.training.zore.basic.app3;

public class DaemonThreadApp {
    public static void main(String[] args) {

        MyDaemonThread t1 = new MyDaemonThread();
        MyDaemonThread t2 = new MyDaemonThread();

        t1.setDaemon(true);
        t1.start();


        //Gives error
        t2.start();
        t2.setDaemon(true);
    }
}

class MyDaemonThread extends Thread {

}
