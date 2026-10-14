package br.unitins.top1.dto;

import br.unitins.top1.model.Accessory;

public record AccessoryResponseDTO(
    Long id,
    String name,
    String category,
    Double price,
    String description
) {
    public static AccessoryResponseDTO fromEntity(Accessory accessory) {
        if (accessory == null) return null;
        return new AccessoryResponseDTO(
            accessory.getId(),
            accessory.getName(),
            accessory.getCategory(),
            accessory.getPrice(),
            accessory.getDescription()
        );
    }
}
