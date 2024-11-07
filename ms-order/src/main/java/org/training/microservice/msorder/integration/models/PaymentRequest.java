package org.training.microservice.msorder.integration.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest   {
  private String orderId = null;
  private String transId = null;
  private BigDecimal amount = null;
}
