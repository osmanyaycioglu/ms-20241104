package org.training.microservice.msorder.integration.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentResponse   {
  private String transId = null;
  private String paymentId = null;
  private String desc = null;
}
