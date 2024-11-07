package org.training.microservice.msaccounting;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder(setterPrefix = "with")
@Jacksonized
public class PaymentResponse {
    private String transId;
    private String paymentId;
    private String desc;
}
