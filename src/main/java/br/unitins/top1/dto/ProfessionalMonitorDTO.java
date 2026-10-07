package br.unitins.top1.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProfessionalMonitorDTO(
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

    @Min(value = 1, message = "Panel type must be between 1 and 4")
    @Max(value = 4, message = "Panel type must be between 1 and 4")
    int idPanelType,

    @NotBlank(message = "Color accuracy is required")
    String colorAccuracy,

    @NotNull(message = "Height adjustment is required")
    Boolean heightAdjustment
) {}
