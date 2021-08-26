package com.example.loadbalancer.module;

public abstract class LoadBalancer {

    /**
     * Abstract method to get backend host based on Load balance strategy.
     * @return host ip.
     */
    public abstract String get();
}