package org.training.microservice.msorder.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training.microservice.msorder.input.mappers.IOrderMapper;
import org.training.microservice.msorder.input.models.OrderDto;
import org.training.microservice.msorder.input.models.OrderResult;
import org.training.microservice.msorder.input.models.Response;
import org.training.microservice.msorder.input.models.SuspendResult;
import org.training.microservice.msorder.services.OrderProcessService;
import org.training.microservice.msorder.services.models.Order;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/v1/order/process")
@Valid
@RequiredArgsConstructor
public class OrderProcessRestController {
    private final OrderProcessService orderProcessService;

    @Value("${server.port}")
    private int port;

    @PostMapping("/place")
    public OrderResult placeOrder(@Valid @RequestBody OrderDto orderDtoParam) {
        Order  orderLoc = IOrderMapper.ORDER_MAPPER.toOrder(orderDtoParam);
        String sLoc = orderProcessService.placeOrder(orderLoc);
        return OrderResult.builder()
                          .withOrderId(orderLoc.getOrderId())
                          .withDeliveryTime(ZonedDateTime.now()
                                                         .plusHours(1))
                          .withNote(sLoc)
                          .build();
    }

    @PostMapping("/place2")
    public OrderResult placeOrder2(@Valid @RequestBody OrderDto orderDtoParam) {
        Order  orderLoc = IOrderMapper.ORDER_MAPPER.toOrder(orderDtoParam);
        String sLoc = orderProcessService.placeOrder2(orderLoc);
        return OrderResult.builder()
                          .withOrderId(orderLoc.getOrderId())
                          .withDeliveryTime(ZonedDateTime.now()
                                                         .plusHours(1))
                          .withNote(sLoc)
                          .build();
    }

    @PostMapping("/place3")
    public OrderResult placeOrder3(@Valid @RequestBody OrderDto orderDtoParam) {
        Order  orderLoc = IOrderMapper.ORDER_MAPPER.toOrder(orderDtoParam);
        String sLoc = orderProcessService.placeOrder3(orderLoc);
        return OrderResult.builder()
                          .withOrderId(orderLoc.getOrderId())
                          .withDeliveryTime(ZonedDateTime.now()
                                                         .plusHours(1))
                          .withNote(sLoc)
                          .build();
    }

    @PostMapping("/place4")
    public OrderResult placeOrder4(@Valid @RequestBody OrderDto orderDtoParam) {
        Order  orderLoc = IOrderMapper.ORDER_MAPPER.toOrder(orderDtoParam);
        String sLoc = orderProcessService.placeOrder4(orderLoc);
        return OrderResult.builder()
                          .withOrderId(orderLoc.getOrderId())
                          .withDeliveryTime(ZonedDateTime.now()
                                                         .plusHours(1))
                          .withNote(sLoc + " order port : " + port)
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
