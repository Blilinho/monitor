package br.unitins.top1.dto;

import java.util.List;

import br.unitins.top1.model.GamingMonitor;
import br.unitins.top1.model.PanelType;

public record GamingMonitorResponseDTO(
    Long id,
    String name,
    Double price,
    Double screenSize,
    PanelType panelType,
    BrandResponseDTO brand,
    Integer refreshRate,
    Double responseTime,
    Boolean gsyncSupport,
    HardwareSpecResponseDTO hardwareSpec,
    List<FeatureResponseDTO> features,
    List<AccessoryResponseDTO> compatibleAccessories
) {
    public static GamingMonitorResponseDTO fromEntity(GamingMonitor monitor) {
        if (monitor == null) return null;
        return new GamingMonitorResponseDTO(
            monitor.getId(),
            monitor.getName(),
            monitor.getPrice(),
            monitor.getScreenSize(),
            monitor.getPanelType(),
            BrandResponseDTO.fromEntity(monitor.getBrand()),
            monitor.getRefreshRate(),
            monitor.getResponseTime(),
            monitor.getGsyncSupport(),
            HardwareSpecResponseDTO.fromEntity(monitor.getHardwareSpec()),
            monitor.getFeatures() == null ? List.of() : monitor.getFeatures().stream().map(FeatureResponseDTO::fromEntity).toList(),
            monitor.getCompatibleAccessories() == null ? List.of() : monitor.getCompatibleAccessories().stream().map(AccessoryResponseDTO::fromEntity).toList()
        );
    }
}
