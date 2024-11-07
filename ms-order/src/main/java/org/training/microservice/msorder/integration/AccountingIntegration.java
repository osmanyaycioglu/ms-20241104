package org.training.microservice.msorder.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.training.microservice.msorder.integration.models.PaymentRequest;
import org.training.microservice.msorder.integration.models.PaymentResponse;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountingIntegration {
    private final RestTemplate restTemplate;


    public String pay(BigDecimal amount,
                      String transId,
                      String orderId) {
        PaymentRequest pr = new PaymentRequest();
        pr.setAmount(amount);
        pr.setTransId(transId);
        pr.setOrderId(orderId);
        PaymentResponse paymentResponseLoc = restTemplate.postForObject("http://ACCOUNTING/api/v1/payment/operations/pay",
                                                                        pr,
                                                                        PaymentResponse.class);
        return paymentResponseLoc.getDesc();
    }

}
