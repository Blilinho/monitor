package br.unitins.top1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record GamingMonitorDTO(
    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Brand is required")
    String brand,

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or greater")
    Double price,

    @NotNull(message = "Screen size is required")
    @Positive(message = "Screen size must be greater than zero")
    Double screenSize,

    @NotNull(message = "Refresh rate is required")
    @Min(value = 1, message = "Refresh rate must be greater than zero")
    Integer refreshRate,

    @NotNull(message = "Response time is required")
    @Positive(message = "Response time must be greater than zero")
    Double responseTime,

    @NotNull(message = "G-Sync support is required")
    Boolean gsyncSupport
) {}
