package org.training.microservice.msorder.integration.resiliency.test;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MyCalleeBean {

    private int counter;

    @CircuitBreaker(name = "my-cb-1",fallbackMethod = "payFallback")
    // @Retry(name = "my-retry-1")
    public String pay4() {
        counter++;
        if(counter < 20) {
            if (counter % 3 == 0) {
                throw new IllegalArgumentException();
            }
        }
        return "OK-" + counter;
    }

    public String payFallback(Throwable throwableParam) {
        System.out.println("-*-*-*-*^+^+^+ Fallback : " + counter);
        return "Fallback OK-" + counter;
    }

}
