package br.unitins.top1.model;

import jakarta.persistence.Entity;

@Entity
public class HardwareSpec extends DefaultEntity {
    private Double weight;
    private String dimensions;
    private Integer powerConsumption;
    
    
}