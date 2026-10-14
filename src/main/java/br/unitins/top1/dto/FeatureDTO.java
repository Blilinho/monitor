package br.unitins.top1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FeatureDTO(
    @NotBlank(message = "Feature name is required.")
    @Size(min = 2, max = 60, message = "Feature name must be between 2 and 60 characters.")
    String name,

    @Size(max = 255, message = "Description cannot exceed 255 characters.")
    String description
) {
}
