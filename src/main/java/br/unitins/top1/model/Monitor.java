package br.unitins.top1.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Monitor extends DefaultEntity {

    @Column(nullable = false)
    private String name;

    private Double price;
    private Double screenSize;

    @Column(name = "id_panel_type")
    private PanelType panelType;

    @ManyToOne
    @JoinColumn(name = "id_brand", nullable = false)
    private Brand brand;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_hardware_spec", unique = true)
    private HardwareSpec hardwareSpec;

    @ManyToMany
    @JoinTable(
        name = "monitor_feature",
        joinColumns = @JoinColumn(name = "id_monitor"),
        inverseJoinColumns = @JoinColumn(name = "id_feature")
    )
    private List<Feature> features = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "monitor_accessory",
        joinColumns = @JoinColumn(name = "id_monitor"),
        inverseJoinColumns = @JoinColumn(name = "id_accessory")
    )
    private List<Accessory> compatibleAccessories = new ArrayList<>();

    public Monitor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Double screenSize) {
        this.screenSize = screenSize;
    }

    public PanelType getPanelType() {
        return panelType;
    }

    public void setPanelType(PanelType panelType) {
        this.panelType = panelType;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public HardwareSpec getHardwareSpec() {
        return hardwareSpec;
    }

    public void setHardwareSpec(HardwareSpec hardwareSpec) {
        this.hardwareSpec = hardwareSpec;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Accessory> getCompatibleAccessories() {
        return compatibleAccessories;
    }

    public void setCompatibleAccessories(List<Accessory> compatibleAccessories) {
        this.compatibleAccessories = compatibleAccessories;
    }
}
