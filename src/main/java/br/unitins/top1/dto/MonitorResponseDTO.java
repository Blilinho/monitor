package br.unitins.top1.dto;

import java.util.List;

import br.unitins.top1.model.Monitor;
import br.unitins.top1.model.PanelType;

public record MonitorResponseDTO(
    Long id,
    String name,
    Double price,
    Double screenSize,
    PanelType panelType,
    BrandResponseDTO brand,
    HardwareSpecResponseDTO hardwareSpec,
    List<FeatureResponseDTO> features,
    List<AccessoryResponseDTO> compatibleAccessories
) {
    public static MonitorResponseDTO fromEntity(Monitor monitor) {
        if (monitor == null) return null;
        return new MonitorResponseDTO(
            monitor.getId(),
            monitor.getName(),
            monitor.getPrice(),
            monitor.getScreenSize(),
            monitor.getPanelType(),
            BrandResponseDTO.fromEntity(monitor.getBrand()),
            HardwareSpecResponseDTO.fromEntity(monitor.getHardwareSpec()),
            monitor.getFeatures() == null ? List.of() : monitor.getFeatures().stream().map(FeatureResponseDTO::fromEntity).toList(),
            monitor.getCompatibleAccessories() == null ? List.of() : monitor.getCompatibleAccessories().stream().map(AccessoryResponseDTO::fromEntity).toList()
        );
    }
}
