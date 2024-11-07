package org.training.microservice.msorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.training.microservice.msorder.integration.AccountingIntegration;
import org.training.microservice.msorder.services.models.Order;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderProcessService {
    private final AccountingIntegration accountingIntegration;

    public String placeOrder(Order orderParam) {
        orderParam.setOrderId(UUID.randomUUID()
                                  .toString());
        return accountingIntegration.pay(new BigDecimal(1000),
                                                  UUID.randomUUID()
                                                      .toString(),
                                                  orderParam.getOrderId());

    }
}
