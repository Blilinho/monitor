package br.unitins.top1.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProfessionalMonitorDTO(
    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters.")
    String name,

    @NotNull(message = "Brand ID is required.")
    @Positive(message = "Brand ID must be positive.")
    Long idBrand,

    @NotNull(message = "Price is required.")
    @Positive(message = "Price must be greater than zero.")
    Double price,

    @NotNull(message = "Screen size is required.")
    @Positive(message = "Screen size must be positive.")
    Double screenSize,

    @Min(value = 1, message = "Panel type must be between 1 and 4.")
    @Max(value = 4, message = "Panel type must be between 1 and 4.")
    int idPanelType,

    @NotBlank(message = "Color accuracy is required.")
    @Size(min = 2, max = 50, message = "Color accuracy must be between 2 and 50 characters.")
    String colorAccuracy,

    @NotNull(message = "Height adjustment is required.")
    Boolean heightAdjustment,

    @Valid
    HardwareSpecDTO hardwareSpec,

    List<Long> featureIds,

    List<Long> accessoryIds
) {
}
