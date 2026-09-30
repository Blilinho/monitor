package br.unitins.top1.dto;

import br.unitins.top1.model.Monitor;
import br.unitins.top1.model.PanelType;

public record MonitorResponseDTO(Long id, String name, String brand, PanelType panelType) {
    public static MonitorResponseDTO fromEntity(Monitor monitor) {
        return new MonitorResponseDTO(
            monitor.getId(),
            monitor.getName(),
            monitor.getBrand(),
            monitor.getPanelType()
        );
    }
}
