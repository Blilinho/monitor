package br.unitins.top1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BrandDTO(
    @NotBlank(message = "Brand name is required.")
    @Size(min = 2, max = 60, message = "Brand name must be between 2 and 60 characters.")
    String name,

    @NotBlank(message = "Country is required.")
    @Size(min = 2, max = 60, message = "Country must be between 2 and 60 characters.")
    String country,

    @Size(max = 120, message = "Website cannot exceed 120 characters.")
    String website
) {
}
