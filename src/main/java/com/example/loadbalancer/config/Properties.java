package com.example.loadbalancer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "config")
@EnableConfigurationProperties
@Data
public class Properties {
    private String maxHostsAllowed;
    private List<String> hosts;
}
