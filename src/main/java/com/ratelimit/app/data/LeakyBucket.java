package com.ratelimit.app.data;

import java.util.LinkedList;
import java.util.Queue;

public class LeakyBucket {

    private final int capacity;
    private final int sustainedRate;
    private final Queue<Object> bucket;

    public LeakyBucket(int capacity, int sustainedRate) {
        this.capacity = capacity;
        this.sustainedRate = sustainedRate;
        this.bucket = new LinkedList<>();
        initializeBucket();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSustainedRate() {
        return sustainedRate;
    }

    private void initializeBucket() {
        for (int i = 0; i < capacity; i++) {
            bucket.offer(new Object());
        }
    }

    public synchronized boolean allowRequest() {
        if (!bucket.isEmpty()) {
            bucket.poll();
            return true;
        }
        return false;
    }

    public void refillBucket() {
        if (bucket.size() < capacity) {
            int tokensToAdd = capacity - bucket.size();
            for (int i = 0; i < tokensToAdd; i++) {
                bucket.offer(new Object());
            }
        }
    }
}
