package com.example.lcnmanager;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagerServer
public class LcnManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LcnManagerApplication.class, args);
    }

}
