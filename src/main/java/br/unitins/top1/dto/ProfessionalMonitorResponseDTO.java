package br.unitins.top1.dto;

import java.util.List;

import br.unitins.top1.model.PanelType;
import br.unitins.top1.model.ProfessionalMonitor;

public record ProfessionalMonitorResponseDTO(
    Long id,
    String name,
    Double price,
    Double screenSize,
    PanelType panelType,
    BrandResponseDTO brand,
    String colorAccuracy,
    Boolean heightAdjustment,
    HardwareSpecResponseDTO hardwareSpec,
    List<FeatureResponseDTO> features,
    List<AccessoryResponseDTO> compatibleAccessories
) {
    public static ProfessionalMonitorResponseDTO fromEntity(ProfessionalMonitor monitor) {
        if (monitor == null) return null;
        return new ProfessionalMonitorResponseDTO(
            monitor.getId(),
            monitor.getName(),
            monitor.getPrice(),
            monitor.getScreenSize(),
            monitor.getPanelType(),
            BrandResponseDTO.fromEntity(monitor.getBrand()),
            monitor.getColorAccuracy(),
            monitor.getHeightAdjustment(),
            HardwareSpecResponseDTO.fromEntity(monitor.getHardwareSpec()),
            monitor.getFeatures() == null ? List.of() : monitor.getFeatures().stream().map(FeatureResponseDTO::fromEntity).toList(),
            monitor.getCompatibleAccessories() == null ? List.of() : monitor.getCompatibleAccessories().stream().map(AccessoryResponseDTO::fromEntity).toList()
        );
    }
}
