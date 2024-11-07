package org.training.microservice.msorder.integration.resiliency.test;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MyCalleeBean {

    private int counter;

    @CircuitBreaker(name = "my-cb-1")
    public String pay4() {
        counter++;
        if (counter % 3 == 0) {
            throw new IllegalArgumentException();
        }
        return "OK-" + counter;
    }

}
