package br.unitins.top1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record HardwareSpecDTO(
    @NotNull(message = "Weight is required.")
    @Positive(message = "Weight must be positive.")
    Double weight,

    @Size(max = 60, message = "Dimensions cannot exceed 60 characters.")
    String dimensions,

    @Positive(message = "Power consumption must be positive.")
    Integer powerConsumption
) {
}
