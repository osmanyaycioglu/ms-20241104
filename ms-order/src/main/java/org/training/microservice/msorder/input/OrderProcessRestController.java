package org.training.microservice.msorder.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training.microservice.msorder.input.models.OrderDto;
import org.training.microservice.msorder.input.models.OrderResult;
import org.training.microservice.msorder.input.models.Response;
import org.training.microservice.msorder.input.models.SuspendResult;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/v1/order/process")
@Valid
public class OrderProcessRestController {

    @PostMapping("/place")
    public OrderResult placeOrder(@Valid @RequestBody OrderDto orderDtoParam) {
        return OrderResult.builder()
                          .withOrderId("o1")
                          .withDeliveryTime(ZonedDateTime.now()
                                                         .plusHours(1))
                          .withNote("test")
                          .build();
    }

    @GetMapping("/suspend")
    public SuspendResult suspendOrder(@NotBlank @RequestParam String orderId) {
        return new SuspendResult("OK");
    }

    public void resumeOrder() {

    }

    public void cancelOrder() {

    }

}