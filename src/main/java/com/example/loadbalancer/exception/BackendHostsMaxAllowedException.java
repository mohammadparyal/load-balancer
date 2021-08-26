package com.example.loadbalancer.exception;


import com.example.loadbalancer.module.BackendHostsPool;

public class BackendHostsMaxAllowedException extends RuntimeException {

    private static final long serialVersionUID = 7315577760622008144L;

    public BackendHostsMaxAllowedException(String backendHostName) {
        super(String.format("Max hosts limit reached of %s is reached, cannot add %s",
                BackendHostsPool.MAX_HOSTS_CAPACITY_ALLOWED, backendHostName));
    }

}