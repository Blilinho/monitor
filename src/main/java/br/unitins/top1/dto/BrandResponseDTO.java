package br.unitins.top1.dto;

import br.unitins.top1.model.Brand;

public record BrandResponseDTO(
    Long id,
    String name,
    String country,
    String website
) {
    public static BrandResponseDTO fromEntity(Brand brand) {
        if (brand == null) return null;
        return new BrandResponseDTO(
            brand.getId(),
            brand.getName(),
            brand.getCountry(),
            brand.getWebsite()
        );
    }
}
