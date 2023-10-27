package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TicketCreateForm {
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 2")
    private String name;
    @NotNull(message = "Number is required")
    @Min(value = 1, message = "Number must be at least 1")
    private Integer number;
}
