package com.example.loadbalancer.exception;

public class RemoveHostsExceptionException extends RuntimeException {

    private static final long serialVersionUID = -3457558552067621614L;

    public RemoveHostsExceptionException(String backendHostName) {
        super("Host cant be removed : " + backendHostName);
    }
}
