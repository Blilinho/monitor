package br.unitins.top1.dto;

import br.unitins.top1.model.PanelType;
import br.unitins.top1.model.ProfessionalMonitor;

public record ProfessionalMonitorResponseDTO(
    Long id,
    String name,
    String brand,
    Double price,
    Double screenSize,
    PanelType panelType,
    String colorAccuracy,
    Boolean heightAdjustment
) {
    public ProfessionalMonitorResponseDTO(ProfessionalMonitor monitor) {
        this(monitor.getId(), monitor.getName(), monitor.getBrand(), monitor.getPrice(),
                monitor.getScreenSize(), monitor.getPanelType(), monitor.getColorAccuracy(),
                monitor.getHeightAdjustment());
    }
}
