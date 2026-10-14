package br.unitins.top1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Accessory extends DefaultEntity {

    @Column(nullable = false)
    private String name;

    private String category;
    private Double price;
    private String description;

    public Accessory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
