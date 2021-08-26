package com.example.loadbalancer.module;

import com.example.loadbalancer.exception.BackendHostsMaxAllowedException;
import com.example.loadbalancer.exception.RemoveHostsExceptionException;

public abstract class LoadBalancer {

    /**
     * Abstract method to get backend host based on Load balance strategy.
     * @return host ip.
     */
    public abstract String get();
}