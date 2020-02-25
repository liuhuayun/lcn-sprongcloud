package com.example.ordersservice.util;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration

public class MvcConfig {
    @Bean
    /**
     * 负载均衡注解 必须加 否则找不到applicatname
     */
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
