package br.unitins.top1.dto;

import br.unitins.top1.model.GamingMonitor;

public record GamingMonitorResponseDTO(
    Long id, 
    String name,
    String brand,
    Double price,
    Double screenSize,
    Integer refreshRate,
    Double responseTime,
    Boolean gsyncSupport
) {
    public GamingMonitorResponseDTO(GamingMonitor monitor) {
        this(
            monitor.getId(), 
            monitor.getName(), 
            monitor.getBrand(), 
            monitor.getPrice(), 
            monitor.getScreenSize(), 
            monitor.getRefreshRate(), 
            monitor.getResponseTime(), 
            monitor.getGsyncSupport()
        );
    }
}