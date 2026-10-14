package br.unitins.top1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AccessoryDTO(
    @NotBlank(message = "Accessory name is required.")
    @Size(min = 2, max = 80, message = "Accessory name must be between 2 and 80 characters.")
    String name,

    @NotBlank(message = "Category is required.")
    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters.")
    String category,

    @NotNull(message = "Price is required.")
    @Positive(message = "Price must be greater than zero.")
    Double price,

    @Size(max = 255, message = "Description cannot exceed 255 characters.")
    String description
) {
}
