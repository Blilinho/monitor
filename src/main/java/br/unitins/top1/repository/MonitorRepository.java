package br.unitins.top1.repository;

import java.util.List;

import br.unitins.top1.model.Monitor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MonitorRepository implements PanacheRepository<Monitor> {

    public List<Monitor> findByName(String name) {
        return find("upper(name) LIKE upper(?1)", "%" + name + "%").list();
    }

    public List<Monitor> findByBrandName(String brandName) {
        return find("upper(brand.name) LIKE upper(?1)", "%" + brandName + "%").list();
    }

    public List<Monitor> findByBrandId(Long brandId) {
        return find("brand.id = ?1", brandId).list();
    }
}
