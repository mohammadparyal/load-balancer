package com.example.loadbalancer.module;


import java.util.List;
import java.util.Random;

/**
 * Random load balancer implementation.
 */
public class RandomLoadBalancer extends LoadBalancer {
    /**
     * Get the backend host based on random load balance strategy.
     * @return host.
     */
    @Override
    public String get() {
        List<String> backendHostsList = BackendHostsPool.getBackendHostsList();
        int randomIndex = new Random().nextInt(backendHostsList.size());
        return backendHostsList.get(randomIndex);
    }

}