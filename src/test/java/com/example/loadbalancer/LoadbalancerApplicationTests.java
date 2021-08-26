package com.example.loadbalancer;

import com.example.loadbalancer.module.BackendHostsPool;
import com.example.loadbalancer.module.LoadBalancer;
import com.example.loadbalancer.module.RandomLoadBalancer;
import com.example.loadbalancer.module.RoundRobinLoadBalancer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class LoadbalancerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void get() {
        testLoadBalancer(new RoundRobinLoadBalancer());
        testLoadBalancer(new RandomLoadBalancer());
    }

    private void testLoadBalancer(LoadBalancer loadBalance) {
        assert(loadBalance.get()).isEmpty();
    }

    @Test
    public void addRemoveTest() {
        BackendHostsPool.remove("https://www.google.com/api/v1/public");
        Assert.isTrue(1 == BackendHostsPool.getBackendHostsMap().size(), "Backend size after removing should be 1");
        BackendHostsPool.add("https://www.google.com/api/v1/public");
        Assert.isTrue(2 == BackendHostsPool.getBackendHostsMap().size(), "Backend size should be now 2");
        BackendHostsPool.disable("https://www.google.com/api/v1/public");
        BackendHostsPool.enable("https://www.google.com/api/v1/public");
        Assert.isTrue(2 == BackendHostsPool.getBackendHostsList().size(), "Backend active host should be 2");
    }

}
