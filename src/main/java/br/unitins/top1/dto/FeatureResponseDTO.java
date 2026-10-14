package br.unitins.top1.dto;

import br.unitins.top1.model.Feature;

public record FeatureResponseDTO(
    Long id,
    String name,
    String description
) {
    public static FeatureResponseDTO fromEntity(Feature feature) {
        if (feature == null) return null;
        return new FeatureResponseDTO(
            feature.getId(),
            feature.getName(),
            feature.getDescription()
        );
    }
}
