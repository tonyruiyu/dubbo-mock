package com.tony.test.protocol;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class DataCountPointer {
    private AtomicInteger idx    = new AtomicInteger(0);

    private Random        random = new Random(6688);

    public int getIdx() {
        return idx.getAndIncrement() % 1000000;
    }

    public Object getRandomInt() {
        return random.nextInt();
    }
}
