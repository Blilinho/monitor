package br.unitins.top1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Monitor extends DefaultEntity {
    private String name;
    private String brand;
    private Double price;
    private Double screenSize;

    @Column(name = "idPanelType")
    private PanelType panelType;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public PanelType getPanelType() { return panelType; }
    public void setPanelType(PanelType panelType) { this.panelType = panelType; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getScreenSize() { return screenSize; }
    public void setScreenSize(Double screenSize) { this.screenSize = screenSize; }
}