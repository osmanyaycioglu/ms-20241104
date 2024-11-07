package org.training.microservice.msaccounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsAccountingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAccountingApplication.class,
                              args);
    }

}
