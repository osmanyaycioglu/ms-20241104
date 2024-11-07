package org.training.microservice.msorder.integration.resiliency.test;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyTestStarter implements CommandLineRunner {
    private final MyCalleeBean           myCalleeBean;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Override
    public void run(final String... args) throws Exception {
        CircuitBreaker circuitBreakerLoc = circuitBreakerRegistry.circuitBreaker("my-cb-1");

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
                String sLoc = myCalleeBean.pay4();
                System.out.println("SUCCESS : " + i + " result : " + sLoc);
            } catch (Exception eParam) {
                System.out.println("**** Exp : " + eParam.getClass()
                                                         .getName());
            }
            CircuitBreaker.Metrics metricsLoc = circuitBreakerLoc.getMetrics();
            System.out.println("CB : "
                               + i
                               + " status : "
                               + circuitBreakerLoc.getState()
                               + " fc : "
                               + metricsLoc.getNumberOfFailedCalls()
                               + " sc : "
                               + metricsLoc.getNumberOfSuccessfulCalls()
                               + " nc : "
                               + metricsLoc.getNumberOfNotPermittedCalls());
        }
    }
}
