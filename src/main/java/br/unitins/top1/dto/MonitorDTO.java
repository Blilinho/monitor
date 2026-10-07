package br.unitins.top1.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record MonitorDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Brand is required")
        String brand,

        @Min(value = 1, message = "Panel type must be between 1 and 4")
        @Max(value = 4, message = "Panel type must be between 1 and 4")
        int idPanelType) {
}
