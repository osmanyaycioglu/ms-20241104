package org.training.microservice.msorder.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.training.microservice.msorder.integration.models.PaymentRequest;
import org.training.microservice.msorder.integration.models.PaymentResponse;

@FeignClient(value = "ACCOUNTING",contextId = "1")
public interface IAccountingFeignClient {

    @PostMapping("/api/v1/payment/operations/pay")
    PaymentResponse pay(@RequestBody PaymentRequest paymentRequestParam);

}
