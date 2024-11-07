package org.training.microservice.msaccounting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payment/operations")
public class PaymentOperationsRestController {

    @Value("${server.port}")
    private Integer port;

    @PostMapping("/pay")
    public PaymentResponse pay(@RequestBody PaymentRequest paymentRequestParam) {
        return PaymentResponse.builder()
                              .withPaymentId(UUID.randomUUID()
                                                 .toString())
                              .withDesc("Payment success : " + port)
                              .withTransId(paymentRequestParam.getTransId())
                              .build();
    }

}
