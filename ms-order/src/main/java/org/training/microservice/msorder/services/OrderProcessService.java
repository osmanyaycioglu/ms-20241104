package org.training.microservice.msorder.services;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.training.microservice.msorder.integration.AccountingIntegration;
import org.training.microservice.msorder.integration.NotificationIntegration;
import org.training.microservice.msorder.services.models.Order;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderProcessService {
    private final AccountingIntegration accountingIntegration;
    private final NotificationIntegration notificationIntegration;

    public String placeOrder(Order orderParam) {
        orderParam.setOrderId(UUID.randomUUID()
                                  .toString());
        return accountingIntegration.pay(new BigDecimal(1000),
                                         UUID.randomUUID()
                                             .toString(),
                                         orderParam.getOrderId());

    }

    public String placeOrder2(Order orderParam) {
        orderParam.setOrderId(UUID.randomUUID()
                                  .toString());
        return accountingIntegration.pay2(new BigDecimal(1000),
                                          UUID.randomUUID()
                                              .toString(),
                                          orderParam.getOrderId());

    }

    public String placeOrder3(Order orderParam) {
        orderParam.setOrderId(UUID.randomUUID()
                                  .toString());
        return accountingIntegration.pay2(new BigDecimal(1000),
                                          UUID.randomUUID()
                                              .toString(),
                                          orderParam.getOrderId());

    }

    public String placeOrder4(Order orderParam) {
        orderParam.setOrderId(UUID.randomUUID()
                                  .toString());
        String sLoc = accountingIntegration.pay4(new BigDecimal(1000),
                                                 UUID.randomUUID()
                                                     .toString(),
                                                 orderParam.getOrderId());
        notificationIntegration.sendEmail("Siparişiniz alındı . Sipariş no : " + orderParam.getOrderId(), orderParam.getCustomerPhone());
        return sLoc;
    }

    @PreDestroy
    public void method(){
        System.out.println("PRE DESTROY");
    }

}

