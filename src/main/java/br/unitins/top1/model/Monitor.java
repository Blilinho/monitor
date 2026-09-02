package br.unitins.top1.model;

import jakarta.persistence.Entity;

@Entity
public class Monitor extends DefaultEntity{
    private String name;
    private String brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


}
