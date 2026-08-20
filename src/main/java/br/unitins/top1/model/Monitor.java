package br.unitins.top1.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Monitor extends PanacheEntity{
    public String name;
    public String brand;
}
