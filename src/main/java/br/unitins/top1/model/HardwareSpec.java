package br.unitins.top1.model;

import jakarta.persistence.Entity;

@Entity
public class HardwareSpec extends DefaultEntity {
    private Double weight;
    private String dimensions;
    private Integer powerConsumption;

    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public String getDimensions() {
        return dimensions;
    }
    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
    public Integer getPowerConsumption() {
        return powerConsumption;
    }
    public void setPowerConsumption(Integer powerConsumption) {
        this.powerConsumption = powerConsumption;
    }
}