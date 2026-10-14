package br.unitins.top1.dto;

import br.unitins.top1.model.HardwareSpec;

public record HardwareSpecResponseDTO(
    Long id,
    Double weight,
    String dimensions,
    Integer powerConsumption
) {
    public static HardwareSpecResponseDTO fromEntity(HardwareSpec spec) {
        if (spec == null) return null;
        return new HardwareSpecResponseDTO(
            spec.getId(),
            spec.getWeight(),
            spec.getDimensions(),
            spec.getPowerConsumption()
        );
    }
}
