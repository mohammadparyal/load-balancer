package com.example.loadbalancer;

import com.example.loadbalancer.module.LoadBalancer;
import com.example.loadbalancer.module.RandomLoadBalancer;
import com.example.loadbalancer.module.RoundRobinLoadBalancer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication
public class LoadbalancerApplication {

    public static void main(String[] args) {
        startLoadBalancer();
    }

    public static void startLoadBalancer() {
        getBackendHostsStrategy(new RandomLoadBalancer());
        getBackendHostsStrategy(new RoundRobinLoadBalancer());
    }


    public static void getBackendHostsStrategy(LoadBalancer loadBalance) {
        execute(loadBalance, 50);
    }

    private static void execute(LoadBalancer loadBalance, int noOfCalls) {
        IntStream
                .range(0, noOfCalls)
                .parallel()
                .forEach(i ->
                        System.out.println(
                                "Host: " + loadBalance.get()
                                        + " Request from Client: " + i
                                        + " [Thread: " + Thread.currentThread().getName() + "]")
                );
    }

}
