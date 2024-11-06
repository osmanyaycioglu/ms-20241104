package org.training.microservice.msorder.input.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@Data
@Builder(setterPrefix = "with")
@Jacksonized
public class OrderResult {
    private String orderId;
    private ZonedDateTime deliveryTime;
    private String note;
}
