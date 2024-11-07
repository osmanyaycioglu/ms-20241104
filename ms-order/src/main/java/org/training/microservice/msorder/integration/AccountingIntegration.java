package org.training.microservice.msorder.integration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.training.microservice.msorder.integration.models.PaymentRequest;
import org.training.microservice.msorder.integration.models.PaymentResponse;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class AccountingIntegration {
    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;
    private       AtomicLong   index = new AtomicLong();
    private List<String> strings = Collections.synchronizedList(new ArrayList<>());
    private Map<String,String> stringStringMap = new ConcurrentHashMap<>();

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

    public String pay2(BigDecimal amount,
                       String transId,
                       String orderId) {
        PaymentRequest pr = new PaymentRequest();
        pr.setAmount(amount);
        pr.setTransId(transId);
        pr.setOrderId(orderId);
        Application        applicationLoc = eurekaClient.getApplication("ACCOUNTING");
        List<InstanceInfo> instancesLoc   = applicationLoc.getInstances();
        int          pointer         = (int) (index.incrementAndGet() % instancesLoc.size());
        InstanceInfo instanceInfoLoc = instancesLoc.get(pointer);
        RestTemplate restTemplateLoc = new RestTemplate();
        PaymentResponse paymentResponseLoc = restTemplateLoc.postForObject("http://"
                                                                           + instanceInfoLoc.getIPAddr()
                                                                           + ":"
                                                                           + instanceInfoLoc.getPort()
                                                                           "/api/v1/payment/operations/pay",
                                                                           pr,
                                                                           PaymentResponse.class);
        return paymentResponseLoc.getDesc();
    }

}
