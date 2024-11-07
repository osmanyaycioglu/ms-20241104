package org.training.microservice.msorder.integration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.training.microservice.mscommon.error.ErrorObj;
import org.training.microservice.msorder.integration.models.PaymentRequest;
import org.training.microservice.msorder.integration.models.PaymentResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class AccountingIntegration {
    private final RestTemplate           restTemplate;
    private final EurekaClient           eurekaClient;
    private final IAccountingFeignClient accountingFeignClient;
    private final RestClient.Builder     restClientBuilder;
    private       AtomicLong             index           = new AtomicLong();
    private       List<String>           strings         = Collections.synchronizedList(new ArrayList<>());
    private       Map<String, String>    stringStringMap = new ConcurrentHashMap<>();

    @Retry(name = "my-retry-1")
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

    // @Retry(name = "my-retry-1")
    public String pay2(BigDecimal amount,
                       String transId,
                       String orderId) {
        PaymentRequest pr = new PaymentRequest();
        pr.setAmount(amount);
        pr.setTransId(transId);
        pr.setOrderId(orderId);
        Application        applicationLoc  = eurekaClient.getApplication("ACCOUNTING");
        List<InstanceInfo> instancesLoc    = applicationLoc.getInstances();
        int                pointer         = (int) (index.incrementAndGet() % instancesLoc.size());
        InstanceInfo       instanceInfoLoc = instancesLoc.get(pointer);
        RestTemplate       restTemplateLoc = new RestTemplate();
        PaymentResponse paymentResponseLoc = restTemplateLoc.postForObject("http://"
                                                                           + instanceInfoLoc.getIPAddr()
                                                                           + ":"
                                                                           + instanceInfoLoc.getPort() +
                                                                           "/api/v1/payment/operations/pay",
                                                                           pr,
                                                                           PaymentResponse.class);
        return paymentResponseLoc.getDesc();
    }

    public String pay3(BigDecimal amount,
                       String transId,
                       String orderId) {
        PaymentRequest pr = new PaymentRequest();
        pr.setAmount(amount);
        pr.setTransId(transId);
        pr.setOrderId(orderId);
        PaymentResponse payLoc = accountingFeignClient.pay(pr);
        return payLoc.getDesc();
    }

    public String pay4(BigDecimal amount,
                       String transId,
                       String orderId) {
        PaymentRequest pr = new PaymentRequest();
        pr.setAmount(amount);
        pr.setTransId(transId);
        pr.setOrderId(orderId);

        ResponseEntity<PaymentResponse> entityLoc = restClientBuilder.build()
                                                                     .post()
                                                                     .uri("http://ACCOUNTING/api/v1/payment/operations/pay")
                                                                     .contentType(MediaType.APPLICATION_JSON)
                                                                     .body(pr)
                                                                     .retrieve()
                                                                     .toEntity(PaymentResponse.class);
        PaymentResponse bodyLoc = entityLoc.getBody();
        return bodyLoc.getDesc();
    }

}
