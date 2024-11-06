package org.training.microservice.msorder.input.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.training.microservice.msorder.validation.CheckWords;

import java.util.List;

@Data
public class OrderDto {
    @NotBlank
    @CheckWords({"abc","xyz","123"})
    private String customerName;
    @NotEmpty
    @Size(min = 3,max = 20,message = "soy isim {min} ile {max} arasında olmalı")
    @CheckWords({"abc","xyz","123"})
    private String customerSurname;
    @CheckWords({"abc","xyz","123"})
    private String customerPhone;
    @NotNull
    @Size(min = 1)
    private List<MealDto> meals;

}
