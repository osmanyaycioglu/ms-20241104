package org.training.microservice.msorder.services.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.training.microservice.msorder.input.models.MealDto;
import org.training.microservice.msorder.validation.CheckWords;

import java.util.List;

@Data
public class Order {
    private String       orderId;
    private String       customerName;
    private String       customerSurname;
    private String       customerPhone;
    private List<Meal>   meals;
    private EOrderStatus orderStatus;
}

