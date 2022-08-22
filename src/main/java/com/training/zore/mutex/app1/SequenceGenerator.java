package com.training.zore.mutex.app1;

public class SequenceGenerator {

    private int currentValue = 0;

    public int getNextSequence() {
        currentValue = currentValue + 1;
        return currentValue;
    }
}
