package br.unitins.top1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Brand extends DefaultEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private String country;
    private String website;

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
