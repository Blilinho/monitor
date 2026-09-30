package br.unitins.top1.dto;

public record ProfessionalMonitorDTO(
    String name,
    String brand,
    Double price,
    Double screenSize,
    int idPanelType,
    String colorAccuracy,
    Boolean heightAdjustment
) {}
