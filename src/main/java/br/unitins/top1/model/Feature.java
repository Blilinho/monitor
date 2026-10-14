package br.unitins.top1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Feature extends DefaultEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    public Feature() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
