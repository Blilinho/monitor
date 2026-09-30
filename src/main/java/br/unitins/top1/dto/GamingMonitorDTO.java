package br.unitins.top1.dto;

public record GamingMonitorDTO(
    String name,
    String brand,
    Double price,
    Double screenSize,
    Integer refreshRate,
    Double responseTime,
    Boolean gsyncSupport
) {}
