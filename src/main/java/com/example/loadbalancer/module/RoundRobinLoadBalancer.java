package com.example.loadbalancer.module;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Round robin load balance.
 */
public class RoundRobinLoadBalancer extends LoadBalancer {

    private int counter = 0;
    private final ReentrantLock lock;

    public RoundRobinLoadBalancer() {
        lock = new ReentrantLock();
    }

    @Override
    public String get() {
        lock.lock();
        try {
            String ip = BackendHostsPool.getBackendHostsList().get(counter);
            counter += 1;
            if (counter == BackendHostsPool.getBackendHostsList().size()) {
                counter = 0;
            }
            return ip;
        } finally {
            lock.unlock();
        }
    }
}