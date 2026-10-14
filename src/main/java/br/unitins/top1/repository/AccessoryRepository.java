package br.unitins.top1.repository;

import java.util.List;

import br.unitins.top1.model.Accessory;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccessoryRepository implements PanacheRepository<Accessory> {

    public List<Accessory> findByName(String name) {
        return find("upper(name) LIKE upper(?1)", "%" + name + "%").list();
    }

    public List<Accessory> findByCategory(String category) {
        return find("upper(category) LIKE upper(?1)", "%" + category + "%").list();
    }
}
