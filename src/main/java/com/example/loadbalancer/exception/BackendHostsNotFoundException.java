package com.example.loadbalancer.exception;


import com.example.loadbalancer.module.BackendHostsPool;

public class BackendHostsNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7315577760622008144L;

    public BackendHostsNotFoundException(String backendHostName) {
        super(String.format("Backend Host not found %s", backendHostName));
    }

}